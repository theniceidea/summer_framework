package com.fmk.framework.exception;

import com.fmk.framework.basic.PropUtil;
import com.fmk.framework.session.GetAcceptLanguage;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Service
@SummerService
public class GetExceptionMessageByCodeService implements SummerServiceBean<GetExceptionMessageByCode> {
    private static ConcurrentHashMap<String, Properties> map = new ConcurrentHashMap<>();
    @Override
    public void sum(GetExceptionMessageByCode summer) {
        String acceptLanguage = GetAcceptLanguage.s();

        if(StringUtil.isBlank(acceptLanguage)) {
            summer.setSummerResult(summer.getDefaultMessage());
            return;
        }

        acceptLanguage = acceptLanguage.trim();
        if(!map.containsKey(acceptLanguage)) {
            map.put(acceptLanguage, PropUtil.fromResource("/language/" + acceptLanguage + ".properties"));
        }
        Properties properties = map.get(acceptLanguage);
        String property = properties.getProperty("ERR_"+summer.getCode(), summer.getDefaultMessage());
        summer.setSummerResult(property);
    }
}
