package com.fmk.framework.exception;

import java.util.Map;

public class LogicNoRollbackException extends LogicException {
    public LogicNoRollbackException(Class<?> fireClass, String codeMsg) {
        super(fireClass, codeMsg);
    }

    public LogicNoRollbackException(Class<?> fireClass, String codeMsg, Map<String, String> params) {
        super(fireClass, codeMsg, params);
    }
}
