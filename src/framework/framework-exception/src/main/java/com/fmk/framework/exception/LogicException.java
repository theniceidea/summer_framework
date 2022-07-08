package com.fmk.framework.exception;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import jodd.util.template.StringTemplateParser;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogicException extends ApplicationException {
    private static StringTemplateParser parser = new StringTemplateParser();

    private String exceptionCode;
    private Class<?> fireClass;
    protected LogicException(String msg){
        super(msg);
    }

    public LogicException(Class<?> fireClass, String code, String msg, boolean replaceMsg){
        super(replaceMsg?replaceLanguageMsg(code, msg):msg);
        this.exceptionCode = code;
        this.fireClass = fireClass;
    }
    public LogicException(Class<?> fireClass, String code, String msg){
        super(replaceLanguageMsg(code, msg));
        this.exceptionCode = code;
        this.fireClass = fireClass;
    }
//    public LogicException(Class<?> fireClass, ErrMsg codeMsg){
//        super(replaceLanguageMsg(codeMsg.getCode(), codeMsg.getMsg()));
//        this.exceptionCode = codeMsg.getCode();
//        this.fireClass = fireClass;
//    }

    public LogicException(Class<?> fireClass, String codeMsg){
        super(replaceLanguageMsg(codeMsg.substring(0, 12), codeMsg.substring(13)));
        this.exceptionCode = codeMsg.substring(0, 12);
        this.fireClass = fireClass;
    }
//    public LogicException(Class<?> fireClass, ErrMsg codeMsg, Map<String, String> params){
//        super(parser.parse(replaceLanguageMsg(codeMsg.getCode(), codeMsg.getMsg()), params::get));
//        this.exceptionCode = codeMsg.getCode();
//        this.fireClass = fireClass;
//    }
    public LogicException(Class<?> fireClass, String codeMsg, Map<String, String> params){
        super(parser.parse(replaceLanguageMsg(codeMsg.substring(0, 12), codeMsg.substring(13)), params::get));
        this.exceptionCode = codeMsg.substring(0, 12);
        this.fireClass = fireClass;
    }
    public static String resetMsg(String msg){
        if(msg.contains("#")){
            List<String> list = Splitter.on("#")
                .omitEmptyStrings()
                .trimResults()
                .splitToList(msg);
            if(list.size()!=2) { throw Excep.ae(msg+" , 此msg的定义不正确"); }
            HashMap map = JSON.parseObject(list.get(1), HashMap.class);
            return parser.parse(list.get(0), s -> map.get(s).toString());
        }
        return msg;
    }
    public static String replaceLanguageMsg(String code, String msg){
        String newMsg = GetExceptionMessageByCode.s(code, msg);
        if(StringUtils.isBlank(newMsg)) { return msg; }
        return newMsg;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public Class<?> getFireClass() {
        return fireClass;
    }

    public void setFireClass(Class<?> fireClass) {
        this.fireClass = fireClass;
    }
}
