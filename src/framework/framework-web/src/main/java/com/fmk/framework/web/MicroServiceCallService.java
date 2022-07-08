package com.fmk.framework.web;

import com.fmk.framework.basic.ThreadLocals;
import org.springframework.stereotype.Component;
import org.summerframework.model.SummerService;

@Component
@SummerService
public class MicroServiceCallService {

    private static final ThreadLocal<Boolean> MICRO_SERVICE_CALL = ThreadLocals.newThreadLocal();

    public void microServiceCall(IsMicroServiceCall summer) {
        Boolean value = MICRO_SERVICE_CALL.get();

        summer.setSummerResult(null == value?false:value);
    }

    public void setMicroServiceCall(SetMicroServiceCall summer){
        MICRO_SERVICE_CALL.set(summer.isMicroServiceCall());
    }

}
