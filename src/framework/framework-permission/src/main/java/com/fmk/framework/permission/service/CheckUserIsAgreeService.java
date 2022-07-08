package com.fmk.framework.permission.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fmk.framework.permission.entities.UserIsAgree;
import com.fmk.framework.permission.entities.UserType;
import com.fmk.framework.validation.Precondition;
import com.fmk.framework.basic.EnumUtil;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.CheckUserIsAgree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import static com.fmk.framework.exception.ErrMsgs.Err_000000000010;
import static com.fmk.framework.exception.ErrMsgs.Err_000200000020;

/**
 * @author larry
 * @date 2019-03-21
 */
@Service
@SummerService
public class CheckUserIsAgreeService implements SummerServiceBean<CheckUserIsAgree> {

    private static final String THE_ONLY_URI_ACCESS_WHEN_USER_IS_NOT_AGREE_TERM = "/portal/term/agree";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sum(CheckUserIsAgree summer) {
        //查询用户是否已经同意协议状态，同意状态的则可以调用接口，没有同意的只能调用一个接口（用户同意协议接口）
        String json = redisTemplate.opsForValue().get(Consts.REDIS_CURRENT_USER + summer.getUserId());
        Precondition.checkState(StringUtils.isNotBlank(json), this.getClass(), Err_000000000010);
        JSONObject jsonObject = JSON.parseObject(json);
        Integer agree = jsonObject.getInteger(Consts.USER_PROPERTY_AGREE_TO_TERMS);
        Integer userType = jsonObject.getInteger(Consts.USER_PROPERTY_USER_TYPE);

        if (EnumUtil.is(UserIsAgree.NOT_AGREE, agree) && needAgreeUserType(userType)) {
            Precondition.checkState(summer.getAgreeUri().contains(THE_ONLY_URI_ACCESS_WHEN_USER_IS_NOT_AGREE_TERM), Err_000200000020);
        }
    }

    private boolean needAgreeUserType(Integer userType){
        return EnumUtil.is(UserType.MEMBER_1888,userType) ||  EnumUtil.is(UserType.MEMBER_VIP,userType);
    }

}
