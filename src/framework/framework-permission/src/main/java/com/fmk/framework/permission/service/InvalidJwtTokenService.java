package com.fmk.framework.permission.service;

import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.InvalidJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import static com.fmk.framework.web.UserAuthInterceptor.STR_BEARER;

/**
 * @author larry
 * @date 2019-05-28
 */
@Service
@SummerService
public class InvalidJwtTokenService implements SummerServiceBean<InvalidJwtToken> {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sum(InvalidJwtToken summer) {
        String sessionValue = (null != summer.getToken() && summer.getToken().startsWith(STR_BEARER + " ")) ? summer.getToken().substring(7).trim() : "";
        redisTemplate.opsForSet().add(Consts.REDIS_JWT_BLACK_LIST_KEY, sessionValue);
    }


}
