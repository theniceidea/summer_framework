package com.fmk.framework.permission.service;

import com.alibaba.fastjson.JSONObject;
import com.fmk.framework.daomodel.DaoOne4Column;
import com.fmk.framework.validation.Precondition;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.permission.entities.AdminUser;
import com.fmk.framework.permission.entities.User;
import com.fmk.framework.web.SetCurrentUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.util.concurrent.TimeUnit;

import static com.fmk.framework.exception.ErrMsgs.Err_000200000016;

/**
 * @author larry
 * @date 2019/2/18
 */
@Service
@SummerService
public class SetCurrentUserInfoService implements SummerServiceBean<SetCurrentUserInfo> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sum(SetCurrentUserInfo summer) {
        if (summer.getAdminUser()) {
            resetAdminUserCache(summer.getUserId());
            return;
        }
        resetPortalUserCache(summer.getUserId());
    }

    private String resetAdminUserCache(String userId) {
        AdminUser user = DaoOne4Column.s(AdminUser.class, AdminUser._id, userId, Err_000200000016);
        Precondition.checkState(StringUtils.isNotBlank(user.getRoleId()), Err_000200000016);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Consts.USER_PROPERTY_ADMIN, Boolean.TRUE);
        jsonObject.put(Consts.USER_PROPERTY_LOGIN_TIME, user.getLoginTime());
        jsonObject.put(Consts.USER_PROPERTY_ID, user.getId());
        jsonObject.put(Consts.USER_PROPERTY_ROLE, user.getRoleId());
        jsonObject.put(Consts.USER_PROPERTY_LOGIN_ID, user.getLoginId());
        jsonObject.put(Consts.USER_PROPERTY_FIRST_NAME, user.getFirstName());
        jsonObject.put(Consts.USER_PROPERTY_LAST_NAME, user.getLastName());
        jsonObject.put(Consts.USER_PROPERTY_FULL_NAME, user.getFullName());
        jsonObject.put(Consts.USER_PROPERTY_NICK_NAME, user.getNickName());
        jsonObject.put(Consts.USER_PROPERTY_MOBILE, user.getMobile());
        jsonObject.put(Consts.USER_PROPERTY_EMAIL, user.getEmail());
        jsonObject.put(Consts.USER_PROPERTY_AVATAR, user.getAvatar());
        jsonObject.put(Consts.USER_PROPERTY_LOGIN_TIME, user.getLoginTime());
        jsonObject.put(Consts.USER_PROPERTY_EXP, DateTime.now().plusYears(1).getMillis() / 1000);

        redisTemplate.opsForValue().set(Consts.REDIS_CURRENT_USER + user.getId(), jsonObject.toJSONString(), 20, TimeUnit.MINUTES);

        return user.getRoleId();
    }

    private void resetPortalUserCache(String userId) {
        User user = DaoOne4Column.s(User.class, User._id, userId, Err_000200000016);

        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(Consts.USER_PROPERTY_ADMIN, Boolean.FALSE);
        jsonObject.put(Consts.USER_PROPERTY_ID, user.getId());
        jsonObject.put(Consts.USER_PROPERTY_USER_TYPE, user.getUserType());
        jsonObject.put(Consts.USER_PROPERTY_LOGIN_ID, user.getLoginId());
        jsonObject.put(Consts.USER_PROPERTY_FIRST_NAME, user.getFirstName());
        jsonObject.put(Consts.USER_PROPERTY_LAST_NAME, user.getLastName());
        jsonObject.put(Consts.USER_PROPERTY_FULL_NAME, user.getFullName());
        jsonObject.put(Consts.USER_PROPERTY_NICK_NAME, user.getNickName());
        jsonObject.put(Consts.USER_PROPERTY_MOBILE, user.getMobile());
        jsonObject.put(Consts.USER_PROPERTY_AREA_CODE, user.getAreaCode());
        jsonObject.put(Consts.USER_PROPERTY_EMAIL, user.getEmail());
        jsonObject.put(Consts.USER_PROPERTY_RANK, user.getRank());
        jsonObject.put(Consts.USER_PROPERTY_AVATAR, user.getAvatar());
        jsonObject.put(Consts.USER_PROPERTY_LOGIN_TIME, user.getLoginTime());
        jsonObject.put(Consts.USER_PROPERTY_AGREE_TO_TERMS, user.getAgreeToTerms());
        jsonObject.put(Consts.USER_PROPERTY_EXP, DateTime.now().plusYears(1).getMillis() / 1000);

        redisTemplate.opsForValue().set(Consts.REDIS_CURRENT_USER + user.getId(), jsonObject.toJSONString(), 20, TimeUnit.MINUTES);
    }
}
