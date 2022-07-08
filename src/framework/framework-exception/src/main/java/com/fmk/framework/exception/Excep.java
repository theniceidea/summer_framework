package com.fmk.framework.exception;

import com.fmk.framework.basic.ErrorMsgCodes;
import com.fmk.framework.basic.Kv;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.StringUtil;
import com.fmk.framework.consts.Consts;
import javassist.*;
import jodd.util.template.StringTemplateParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Excep {
    private static StringTemplateParser parser = new StringTemplateParser();
    private static ClassPool CLASS_POOL = null;
    private static final HashMap<String, Class<? extends LogicException>> LOGIC_EXCEPTION_CLASS_CACHE = new HashMap<>();

    static {
        CLASS_POOL = ClassPool.getDefault();
        ClassClassPath classClassPath = new ClassClassPath(Excep.class);
        CLASS_POOL.insertClassPath(classClassPath);
    }

//    public static String code(String codeMsg){
//        return codeMsg.substring(0, 12);
//    }
//    public static String msg(String codeMsg){
//        return codeMsg.substring(13);
//    }

    public static ApplicationException ae(String message){
        return new ApplicationException(message);
    }
    public static ApplicationException ae(String message, Throwable cause){
        return new ApplicationException(message, cause);
    }
    public static ApplicationException ae(Throwable cause){
        return new ApplicationException(cause);
    }
    public static ApplicationException ae(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        return new ApplicationException(message, cause, enableSuppression, writableStackTrace);
    }

    public static LogicException le(Class<?> fireClass, String code, String msg, boolean replaceMsg) {
        return newLogicException(fireClass, code, msg, replaceMsg);
    }
    public static LogicException le(Class<?> fireClass, String code, String msg) {
        return newLogicException(fireClass, code, msg, false);
    }

    public static LogicException le(Class<?> fireClass, String codeMsg) {
        final String code = ErrorMsgCodes.getErrCode(codeMsg);
        String msg = LogicException.replaceLanguageMsg(code, codeMsg);
//        String code = codeMsg.substring(0, 12);
        return newLogicException(fireClass, code, msg, false);
    }
    public static LogicException le(String errMsg) {
        return le(Excep.class, errMsg);
    }

    public static LogicException le(Class<?> fireClass, String codeMsg, Map<String, String> params) {
        final String code = ErrorMsgCodes.getErrCode(codeMsg);
        String msg = parser.parse(LogicException.replaceLanguageMsg(code, codeMsg), params::get);
//        String code = codeMsg.substring(0, 12);
        return newLogicException(fireClass, code, msg, false);
    }

    public static LogicException leNoRollback(Class<?> fireClass, String codeMsg) {
        return new LogicNoRollbackException(fireClass, codeMsg);
    }

    public static LogicException leNoRollback(Class<?> fireClass, String codeMsg, Map<String, String> params) {
        return new LogicNoRollbackException(fireClass, codeMsg, params);
    }
    public static <T extends Throwable> T findSuperException(Throwable exception, Class<T> superClass){
        Throwable e = exception;
        while (null != e){
            if(Objects.equals(e.getClass(), superClass) || superClass.isAssignableFrom(e.getClass())) return (T) e;
            e = e.getCause();
        }
        return null;
    }
    public static Throwable findSuperException(Throwable exception, String superClassName){
        Class<? extends Throwable> sClass = (Class<? extends Throwable>) ReflectUtil.getClassNoThrow(superClassName);
        if(null != sClass){
            return Excep.findSuperException(exception, sClass);
        }

        Throwable e = exception;
        while (null != e){
            if(Objects.equals(e.getClass().getName(), superClassName)) {
                return e;
            }
            e = e.getCause();
        }
        return null;
    }
    public static String getExceptionContent(Exception e) throws IOException {
        try(StringWriter stringWriter = new StringWriter()) {
            try(PrintWriter printWriter = new PrintWriter(stringWriter)) {
                e.printStackTrace(printWriter);
                return stringWriter.toString();
            }
        }
    }
    public static List<String> getErrMsgs(String modName) {
        String pkg = Consts.SYSTEM_PKG_COM_COMP_PREFIX +".model";
        Class<?> errMsgsKls = ReflectUtil.getClass(pkg+"." + modName.toLowerCase() + "." + StringUtil.firstCharUpper(modName) + "ErrMsgs");
        ArrayList<String> result = new ArrayList();
        if (null != errMsgsKls) {
            ReflectUtil.eachFields(errMsgsKls, (field) -> {
                if (String.class.equals(field.getType())) {
                    if (field.getName().startsWith("Err_")) {
                        result.add((String)ReflectUtil.fieldValueNoThrow(field, (Object)null));
                    }
                }
            });
        }

        return result;
    }

    public static LogicException newLogicException(Class<?> kls, String errCode, String msg, boolean replaceMsg){
        try {
            return newLogicException_(kls, errCode, msg, replaceMsg);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static LogicException newLogicException_(Class<?> kls, String errCode, String msg, boolean replaceMsg)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        msg = replaceMsg?LogicException.replaceLanguageMsg(errCode, msg):msg;

        Class<? extends LogicException> logicExceptionClasses = createLogicExceptionClasses(errCode);
        if(null == logicExceptionClasses){
            return new LogicException(kls, errCode, msg, replaceMsg);
        }
        Constructor<? extends LogicException> constructor = logicExceptionClasses.getConstructor(String.class);
        LogicException ex = constructor.newInstance(msg);
        ex.setFireClass(kls);
        ex.setExceptionCode(errCode);
        return ex;
    }
    private static Class<? extends LogicException> createLogicExceptionClasses(String errCode){
        try {
            return createLogicExceptionClasses_(errCode);
        } catch (NotFoundException | CannotCompileException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Class<? extends LogicException> createLogicExceptionClasses_(String errCode)
        throws NotFoundException, CannotCompileException {
        Class<? extends LogicException> kls = LOGIC_EXCEPTION_CLASS_CACHE.get(errCode);
        if(null != kls) { return kls; }
        synchronized (Excep.class){
            kls = LOGIC_EXCEPTION_CLASS_CACHE.get(errCode);
            if(null != kls) { return kls; }
            String klsName = "LogicException_"+errCode;
            CtClass ctClass = CLASS_POOL.makeClass(klsName);
            ctClass.setSuperclass(CLASS_POOL.get(LogicException.class.getName()));
            CtClass skls = CLASS_POOL.get(String.class.getName());
            CtConstructor constructor = new CtConstructor(new CtClass[]{skls}, ctClass);
            constructor.setBody("{ super($1); }");
            ctClass.addConstructor(constructor);
            kls = (Class<? extends LogicException>) ctClass.toClass();
            LOGIC_EXCEPTION_CLASS_CACHE.put(errCode, kls);
        }
        return kls;
    }
    public static class TTT extends LogicException{

        protected TTT(String msg) {
            super(msg);
        }
    }
//    public static void main(String[] args){
//        System.out.println(Excep.le(Excep.class, "000011110000", "sdfasdf"));
//        System.out.println(Excep.le(Excep.class, "000011110000", "sdfasdf"));
//        System.out.println(Excep.le(Excep.class, "000011110001_sdfasdf"));
//        System.out.println(Excep.le(Excep.class, "000011110001_sdfasdf"));
//        System.out.println(Excep.le(Excep.class, "000011110001_sdfasdf"));
//        System.out.println(Excep.le(Excep.class, "000011110002", "sdfasdf", true));
//        System.out.println(Excep.le(Excep.class, "000011110002", "sdfasdf", false));
//        System.out.println(Excep.le(Excep.class, "000011110002", "sdfasdf", true));
//        System.out.println(Excep.le(Excep.class, "000011110002", "sdfasdf", false));
//        System.out.println(Excep.le(Excep.class, "000011110003_sdfasdf${mm}", Kv.inst("mm", "999")));
//        System.out.println(Excep.le(Excep.class, "000011110003_sdfasdf${mm}", Kv.inst("mm", "999")));


//        Exception exception = new Exception("xxx", Excep.le(Excep.class, "000000001111", "xxxddsfsd"));
//        LogicException superException = Excep.findSuperException(exception, LogicException.class);
//        System.out.println(superException);
//
//        Exception exception1 = new Exception("xxx", new TTT(Excep.class, "000000001111", "xxxddsfsd9werwer23"));
//        LogicException superException1 = (LogicException) Excep.findSuperException(exception1, LogicException.class.getName());
//        System.out.println(superException1);
//    }
}
