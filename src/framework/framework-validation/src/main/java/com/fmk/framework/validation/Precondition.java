package com.fmk.framework.validation;

import com.fmk.framework.basic.Validator;
//import com.fmk.framework.exception.ErrMsg;
import com.fmk.framework.exception.Excep;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by jerry on 16-8-30.
 */
public class Precondition {

    public static void validation(Object bean){
        String message = Validator.validation(bean);
        if(StringUtils.isBlank(message)){
            return;
        }
        throw Excep.le(Precondition.class, message);
    }

//    public static void checkArgument(boolean bol, Class<?> fireClass, String message){
//        if(bol) throw new LogicException(fireClass, message);
//    }
//    public static void checkState(boolean bol, ErrMsg message){
//        if(!bol) throw new LogicException(Precondition.class, message);
//    }
    public static void checkState(boolean bol, String message){
        if(!bol) throw Excep.le(Precondition.class, message);
    }
    public static void checkState(boolean bol, String message, Map<String, String> params){
        if(!bol) throw Excep.le(Precondition.class, message, params);
    }

//    public static void checkState(boolean bol, Class<?> fireClass, ErrMsg message){
//        if(!bol) throw new LogicException(fireClass, message);
//    }
    public static void checkState(boolean bol, Class<?> fireClass, String message){
        if(!bol) throw Excep.le(fireClass, message);
    }
//    public static <T> T checkNotNull(T reference, ErrMsg message){
//        if(null == reference) throw new LogicException(Precondition.class, message);
//        return reference;
//    }

    public static <T> T checkNotNull(T reference, String message){
        if(null == reference) throw Excep.le(Precondition.class, message);
        return reference;
    }

    public static <T> T checkNotNull(T reference, String message, Map<String, String> params){
        if(null == reference) throw Excep.le(Precondition.class, message, params);
        return reference;
    }
//    public static <T> T checkNotNull(T reference, Class<?> fireClass, ErrMsg message){
//        if(null == reference) throw new LogicException(fireClass, message);
//        return reference;
//    }

    public static <T> T checkNotNull(T reference, Class<?> fireClass, String message){
        if(null == reference) throw Excep.le(fireClass, message);
        return reference;
    }
}
