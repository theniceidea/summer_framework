package com.fmk.framework.web;

import com.fmk.framework.session.GetAcceptLanguage;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

@Service
@SummerService
public class GetAcceptLanguageService implements SummerServiceBean<GetAcceptLanguage> {
    @Override
    public void sum(GetAcceptLanguage summer) {
        String language = GetRequestHeader.s("Accept-Language");

        summer.setSummerResult(language);
    }
}
