package com.fmk.framework.permission.service;

import com.fmk.framework.validation.Precondition;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.CheckJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import static com.fmk.framework.exception.ErrMsgs.Err_000000000010;
import static com.fmk.framework.exception.ErrMsgs.Err_000000000023;

/**
 * @author larry
 * @date 2019-05-28
 */
@Service
@SummerService
public class CheckJwtTokenService implements SummerServiceBean<CheckJwtToken> {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sum(CheckJwtToken summer) {
        Boolean isInvalid = redisTemplate.opsForSet().isMember(Consts.REDIS_JWT_BLACK_LIST_KEY, summer.getToken());
        Precondition.checkState(!isInvalid, this.getClass(), Err_000000000010);
        checkBlacklist(summer.getUserId());
    }

    private void checkBlacklist(String userId) {
        Boolean isInvalid = redisTemplate.opsForSet().isMember(Consts.REDIS_USER_BLACK_LIST_KEY, userId);
        Precondition.checkState(!(null != isInvalid && isInvalid), this.getClass(), Err_000000000023);
    }

}
