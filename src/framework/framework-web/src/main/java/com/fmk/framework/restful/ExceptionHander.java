package com.fmk.framework.restful;

import cn.hutool.core.util.URLUtil;
import com.fmk.framework.basic.ErrorMsgCodes;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.ErrMsgs;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.exception.LogicException;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.web.SetResponseHeader;
//import com.netflix.hystrix.exception.HystrixRuntimeException;
//import com.fmk.framework.exception.ErrMsg;
import com.fmk.framework.web.IsMicroServiceCall;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static java.util.Objects.nonNull;

@ControllerAdvice
public class ExceptionHander {
    private static Logger logger = Logger.getLogger(ExceptionHander.class);
    private static final String SENTINEL_DEGRADE_EXCEPTION = "com.alibaba.csp.sentinel.slots.block.degrade.DegradeException";
    private static final String SENTINEL_FLOW_EXCEPTION = "com.alibaba.csp.sentinel.slots.block.flow.FlowException";
    private static final String ACCESS_DENIED_EXCEPTION = "org.springframework.security.access.AccessDeniedException";


    private void setMicroServiceHeader(RestResult result) {
        boolean bol = IsMicroServiceCall.s();
        if (!bol) {
            return;
        }
        SetResponseHeader.s(Consts.SERVICE_RESULT_CODE, result.getCode());
        SetResponseHeader.s(Consts.SERVICE_RESULT_MSG, URLUtil.encode(result.getMsg()));
    }

//    @ExceptionHandler(value = AuthenticationException.class)
//    @ResponseBody
//    public RestResult<Void> authenticationExceptionHandler(HttpServletRequest req, AuthenticationException e) {
////        logger.error(e.getMessage(), e);
//        RestResult<Void> result = new RestResult<>();
//        result.setCode(Err_000000000010.getCode());
//        result.setMsg(Err_000000000010.getMsg());
//
//        result.setMsg(GetExceptionMessageByCode.s(result.getCode(), result.getMsg()));
//        setMicroServiceHeader(result);
//        return result;
//    }
//
//    @ExceptionHandler(value = UnauthenticatedException.class)
//    @ResponseBody
//    public RestResult<Void> unauthenticatedExceptionHandler(HttpServletRequest req, UnauthenticatedException e) {
////        logger.error(e.getMessage(), e);
//        RestResult<Void> result = new RestResult<>();
//        result.setCode(Err_000000000010.getCode());
//        result.setMsg(Err_000000000010.getMsg());
//
//        result.setMsg(GetExceptionMessageByCode.s(result.getCode(), result.getMsg()));
//        setMicroServiceHeader(result);
//        return result;
//    }

    @ExceptionHandler(value = LogicException.class)
    @ResponseBody
    public RestResult<Void> logicExceptionHandler(HttpServletRequest req, LogicException e) {
        logger.error(e.getMessage(), e);
        RestResult<Void> result = new RestResult<>();
        result.setCode(e.getExceptionCode());
        result.setMsg(e.getMessage());

//        result.setMsg(GetExceptionMessageByCode.s(result.getCode(), result.getMsg()));
        setMicroServiceHeader(result);
        return result;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public RestResult<Void> constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        RestResult<Void> result = new RestResult<>();

        if (e.getConstraintViolations().size() <= 0) {
            return exceptionHandler(req, e);
        }
        ConstraintViolation<?> violation = e.getConstraintViolations()
                .iterator()
                .next();

        result.setCode(violation.getMessageTemplate().substring(0, 12));
        String substring = violation.getMessageTemplate()
                .substring(13);
        result.setMsg(LogicException.resetMsg(substring));

//        result.setMsg(GetExceptionMessageByCode.s(result.getCode(), result.getMsg()));
        setMicroServiceHeader(result);
        return result;
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    @ResponseBody
    public RestResult<Void> transactionSystemExceptionHandler(HttpServletRequest req, TransactionSystemException e) {

//        if(null != e.getCause()) {
//            Throwable cause = e.getCause();
//            if(null != cause.getCause()){
//                Throwable cause1 = cause.getCause();
//                if(cause1 instanceof ConstraintViolationException){
//                    return constraintViolationExceptionHandler(req, (ConstraintViolationException) cause1);
//                }
//            }
//        }

        ConstraintViolationException ce = Excep.findSuperException(e, ConstraintViolationException.class);
        if (nonNull(ce)) {
            return constraintViolationExceptionHandler(req, ce);
        }
        logger.error(e.getMessage(), e);

        RestResult<Void> result = new RestResult<>();
//        result.setCode(Excep.code(ErrMsgs.Err_000000000009));
//        result.setMsg(Excep.msg(ErrMsgs.Err_000000000009));
        result.setCode(ErrorMsgCodes.getErrCode(ErrMsgs.Err_000000000009));
        result.setMsg(ErrMsgs.Err_000000000009);
        result.setMsg(LogicException.replaceLanguageMsg(result.getCode(), result.getMsg()));

//        result.setMsg(GetExceptionMessageByCode.s(result.getCode(), result.getMsg()));
        setMicroServiceHeader(result);
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResult<Void> exceptionHandler(HttpServletRequest req, Exception e) {
//        UnauthenticatedException ue = Excep.findSuperException(e, UnauthenticatedException.class);
//        if (nonNull(ue)) {
//            return unauthenticatedExceptionHandler(req, ue);
//        }
        LogicException le = Excep.findSuperException(e, LogicException.class);
        if (nonNull(le)) {
            return logicExceptionHandler(req, le);
        }

        boolean isMicroServiceCall = IsMicroServiceCall.s();
        if (isMicroServiceCall && nonNull(Excep.findSuperException(e, SENTINEL_FLOW_EXCEPTION))) {
            return sentinelExceptionHandler(ErrMsgs.Err_000000000013);
        }
        if (isMicroServiceCall && nonNull(Excep.findSuperException(e, SENTINEL_DEGRADE_EXCEPTION))) {
            return sentinelExceptionHandler(ErrMsgs.Err_000000000015);
        }
        if (nonNull(Excep.findSuperException(e, ACCESS_DENIED_EXCEPTION))) {
            return accessDeniedExceptionHandler(ErrMsgs.Err_000200000016);
        }
        if (isNotMicroServiceCallSentinelException(e)) {
            return sentinelExceptionHandler(ErrMsgs.Err_000000000008);
        }

//        HystrixRuntimeException hre = Excep.findSuperException(e, HystrixRuntimeException.class);
//        if (nonNull(hre)) {
//            le = Excep.findSuperException(hre.getFallbackException(), LogicException.class);
//        }
        if (nonNull(le)) {
            return logicExceptionHandler(req, le);
        }

        ConstraintViolationException ce = Excep.findSuperException(e, ConstraintViolationException.class);
        if (nonNull(ce)) {
            return constraintViolationExceptionHandler(req, ce);
        }
        TransactionSystemException te = Excep.findSuperException(e, TransactionSystemException.class);
        if (nonNull(te)) {
            return transactionSystemExceptionHandler(req, te);
        }
        logger.error(e.getMessage(), e);

        RestResult<Void> result = new RestResult<>();
//        result.setCode(Excep.code(ErrMsgs.Err_000000000009));
//        result.setMsg(Excep.msg(ErrMsgs.Err_000000000009));
        result.setCode(ErrorMsgCodes.getErrCode(ErrMsgs.Err_000000000009));
        result.setMsg(ErrMsgs.Err_000000000009);
        result.setMsg(LogicException.replaceLanguageMsg(result.getCode(), result.getMsg()));

//        result.setMsg(GetExceptionMessageByCode.s(result.getCode(), result.getMsg()));
        setMicroServiceHeader(result);
        return result;
    }

    private RestResult<Void> sentinelExceptionHandler(String errMsg) {
        RestResult<Void> result = new RestResult<>();
//        result.setCode(Excep.code(errMsg));
//        result.setMsg(Excep.msg(errMsg));
        result.setCode(ErrorMsgCodes.getErrCode(errMsg));
        result.setMsg(errMsg);
        setMicroServiceHeader(result);
        return result;
    }

    private RestResult<Void> accessDeniedExceptionHandler(String errMsg) {
        RestResult<Void> result = new RestResult<>();
//        result.setCode(Excep.code(errMsg));
//        result.setMsg(Excep.msg(errMsg));
        result.setCode(ErrorMsgCodes.getErrCode(errMsg));
        result.setMsg(errMsg);
        setMicroServiceHeader(result);
        return result;
    }

    private boolean isNotMicroServiceCallSentinelException(Throwable exception) {
        return (!IsMicroServiceCall.s())
                && (nonNull(Excep.findSuperException(exception, SENTINEL_DEGRADE_EXCEPTION))
                || nonNull(Excep.findSuperException(exception, SENTINEL_DEGRADE_EXCEPTION)));
    }

}
