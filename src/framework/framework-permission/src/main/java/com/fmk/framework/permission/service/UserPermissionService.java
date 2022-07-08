//package com.fmk.framework.permission.service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import AdminUser;
//import User;
//import UserRoleAcl;
//import com.fmk.framework.permission.model.UserPermission;
//import com.google.common.collect.Lists;
//import Consts;
//import DaoOne4Column;
//import org.apache.commons.lang3.StringUtils;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.stereotype.Service;
//import org.summerframework.model.SummerService;
//import org.summerframework.model.SummerServiceBean;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import static ErrMsgs.Err_000200000016;
//
///**
// * @author larry
// * @date 2019/2/18
// */
//@Service
//@SummerService
//public class UserPermissionService implements SummerServiceBean<UserPermission> {
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Override
//    public void sum(UserPermission summer) {
//        String roleId = getUserRoleId(summer);
//        List<String> rights = Lists.newArrayList();
//        if (summer.getAdminUser()) {
//            rights = getAdminUserRoleRights(roleId);
//        }
//        summer.setSummerResult(rights);
//    }
//
//    private String getUserRoleId(UserPermission summer) {
//        String json = redisTemplate.opsForValue().get(Consts.REDIS_CURRENT_USER + summer.getUserId());
//
//        if (StringUtils.isBlank(json)) {
//            return resetUserCache(summer.getUserId(), summer.getAdminUser());
//        }
//
//        JSONObject jsonObject = JSON.parseObject(json);
//        String roleId = (String) jsonObject.get(Consts.USER_PROPERTY_ROLE);
//
//        return (StringUtils.isBlank(roleId) && summer.getAdminUser()) ? resetAdminUserCache(summer.getUserId()) : roleId;
//    }
//
//    private List<String> getAdminUserRoleRights(String roleId) {
//        if (!redisTemplate.opsForHash().hasKey(Consts.REDIS_ROLES_KEY, roleId)) {
//            UserRoleAcl userRoleAcl = DaoOne4Column.s(UserRoleAcl.class, UserRoleAcl._rolesId, roleId, Err_000200000016.fullMsg());
//            redisTemplate.opsForHash().put(Consts.REDIS_ROLES_KEY, roleId, userRoleAcl.getRightsJson());
//        }
//
//        String json = (String) redisTemplate.opsForHash().get(Consts.REDIS_ROLES_KEY, roleId);
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        List<String> rights = (List<String>) jsonObject.get(Consts.USER_ROLE_RIGHTS);
//
//        return rights;
//    }
//
//    private String resetUserCache(String userId, Boolean adminUser) {
//        if (adminUser) {
//            return resetAdminUserCache(userId);
//        }
//        resetPortalUserCache(userId);
//        return null;
//    }
//
//    private String resetAdminUserCache(String userId) {
//        AdminUser user = DaoOne4Column.s(AdminUser.class, AdminUser._id, userId, Err_000200000016.fullMsg());
//        if (StringUtils.isBlank(user.getRoleId())) {
//            throw new AccessDeniedException(Err_000200000016.getMsg());
//        }
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(Consts.USER_PROPERTY_ADMIN, Boolean.TRUE);
//        jsonObject.put(Consts.USER_PROPERTY_ID, user.getId());
//        jsonObject.put(Consts.USER_PROPERTY_ROLE, user.getRoleId());
//        jsonObject.put(Consts.USER_PROPERTY_LOGIN_ID, user.getLoginId());
//        jsonObject.put(Consts.USER_PROPERTY_FIRST_NAME, user.getFirstName());
//        jsonObject.put(Consts.USER_PROPERTY_LAST_NAME, user.getLastName());
//        jsonObject.put(Consts.USER_PROPERTY_FULL_NAME, user.getFullName());
//        jsonObject.put(Consts.USER_PROPERTY_NICK_NAME, user.getNickName());
//        jsonObject.put(Consts.USER_PROPERTY_MOBILE, user.getMobile());
//        jsonObject.put(Consts.USER_PROPERTY_EMAIL, user.getEmail());
//        jsonObject.put(Consts.USER_PROPERTY_AVATAR, user.getAvatar());
//        jsonObject.put(Consts.USER_PROPERTY_LOGIN_TIME, user.getLoginTime());
//        jsonObject.put(Consts.USER_PROPERTY_EXP, DateTime.now().plusYears(1).getMillis() / 1000);
//
//        redisTemplate.opsForValue().set(Consts.REDIS_CURRENT_USER + user.getId(), jsonObject.toJSONString(), 20, TimeUnit.MINUTES);
//
//        return user.getRoleId();
//    }
//
//    private void resetPortalUserCache(String userId) {
//        User user = DaoOne4Column.s(User.class, User._id, userId, Err_000200000016.fullMsg());
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(Consts.USER_PROPERTY_ADMIN, Boolean.TRUE);
//        jsonObject.put(Consts.USER_PROPERTY_ID, user.getId());
//        jsonObject.put(Consts.USER_PROPERTY_LOGIN_ID, user.getLoginId());
//        jsonObject.put(Consts.USER_PROPERTY_FIRST_NAME, user.getFirstName());
//        jsonObject.put(Consts.USER_PROPERTY_LAST_NAME, user.getLastName());
//        jsonObject.put(Consts.USER_PROPERTY_FULL_NAME, user.getFullName());
//        jsonObject.put(Consts.USER_PROPERTY_NICK_NAME, user.getNickName());
//        jsonObject.put(Consts.USER_PROPERTY_MOBILE, user.getMobile());
//        jsonObject.put(Consts.USER_PROPERTY_EMAIL, user.getEmail());
//        jsonObject.put(Consts.USER_PROPERTY_RANK, user.getRank());
//        jsonObject.put(Consts.USER_PROPERTY_AVATAR, user.getAvatar());
//        jsonObject.put(Consts.USER_PROPERTY_LOGIN_TIME, user.getLoginTime());
//        jsonObject.put(Consts.USER_PROPERTY_AUTH_TYPE, user.getAuthType());
//        jsonObject.put(Consts.USER_PROPERTY_AUTH_OPEN_ID, user.getAuthOpenId());
//        jsonObject.put(Consts.USER_PROPERTY_AUTH_UNION_ID, user.getAuthUnionId());
//        jsonObject.put(Consts.USER_PROPERTY_EXP, DateTime.now().plusYears(1).getMillis() / 1000);
//
//        redisTemplate.opsForValue().set(Consts.REDIS_CURRENT_USER + user.getId(), jsonObject.toJSONString(), 20, TimeUnit.MINUTES);
//    }
//}
