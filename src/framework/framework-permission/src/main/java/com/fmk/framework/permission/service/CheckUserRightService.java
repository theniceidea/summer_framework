package com.fmk.framework.permission.service;

import com.alibaba.fastjson.JSONObject;
import com.fmk.framework.daomodel.DaoOne4Column;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.permission.entities.AdminUser;
import com.fmk.framework.permission.entities.UserRoleAcl;
import com.fmk.framework.validation.Precondition;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.CheckUserRight;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;
import org.summerframework.model.SummerServiceBean;

import java.util.List;

import static com.fmk.framework.exception.ErrMsgs.Err_000200000016;

/**
 * @author larry
 * @date 2019/2/18
 */
@Service
@SummerService
public class CheckUserRightService implements SummerServiceBean<CheckUserRight> {
    private static final Logger logger = Logger.getLogger(CheckUserRightService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sum(CheckUserRight summer) {
        String roleId = getUserRoleId(summer.getUserId());
        List<String> rights = getAdminUserRoleRights(roleId);
        checkRight(summer.getHasAuthority(), rights);
    }

    private String getUserRoleId(String userId) {
        AdminUser user = DaoOne4Column.s(AdminUser.class, AdminUser._id, userId, Err_000200000016);
        Precondition.checkState(StringUtils.isNotBlank(user.getRoleId()), Err_000200000016);
        return user.getRoleId();
    }

    private List<String> getAdminUserRoleRights(String roleId) {
        if (!redisTemplate.opsForHash().hasKey(Consts.REDIS_ROLES_KEY, roleId)) {
            UserRoleAcl userRoleAcl = DaoOne4Column.s(UserRoleAcl.class, UserRoleAcl._rolesId, roleId, Err_000200000016);
            redisTemplate.opsForHash().put(Consts.REDIS_ROLES_KEY, roleId, userRoleAcl.getRightsJson());
        }

        String json = (String) redisTemplate.opsForHash().get(Consts.REDIS_ROLES_KEY, roleId);
        JSONObject jsonObject = JSONObject.parseObject(json);
        List<String> rights = (List<String>) jsonObject.get(Consts.USER_ROLE_RIGHTS);

        return rights;
    }

    private void checkRight(String hasAuthority, List<String> rights) {
//        logger.info("===========right check right:{} rights:{}", hasAuthority, rights);
        Precondition.checkState(rights.contains(hasAuthority), Err_000200000016);
    }
}
