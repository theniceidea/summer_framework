//package com.fmk.framework.permission.config;
//
//import Consts;
//import com.fmk.framework.permission.model.UserPermission;
//import Precondition;
//import JwtService;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import static ErrMsgs.Err_000000000010;
//
///**
// * @author larry
// * @date 2019-02-13
// */
//@Component
//public class PermissionFilter extends OncePerRequestFilter {
//
//    @Autowired
//    JwtService jwtService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws IOException, ServletException {
//        UserProfile profile = createUserProfile();
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                profile, null, profile.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * 创建登录用户对象
//     *
//     * @return UserProfile
//     */
//    private UserProfile createUserProfile() {
//        Map decode = getUserInfoFromJwt();
//
//        String currentUserId = (String) decode.get(Consts.USER_PROPERTY_ID);
//        Precondition.checkState(StringUtils.isNotBlank(currentUserId), this.getClass(), Err_000000000010);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setRealName("USER");
//        userProfile.setUserId(currentUserId);
//
//        Boolean adminUser = decode.containsKey(Consts.USER_PROPERTY_ADMIN);
//        List<String> permissions = getUserPermissions(currentUserId, adminUser);
//        userProfile.setPermissionCodes(permissions);
//
//        return userProfile;
//    }
//
//    /**
//     * 从jwt中获取用户信息
//     *
//     * @return 用户Map信息
//     */
//    private Map<String, Object> getUserInfoFromJwt() {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        String header = request.getHeader("Authorization");
//
//        String sessionValue = (null != header && header.startsWith("Bearer ")) ? header.substring(7).trim() : "";
//        if (StringUtils.isBlank(sessionValue)) {
//            return Collections.emptyMap();
//        }
//
//        return jwtService.decodeAndVerify(sessionValue);
//    }
//
//    /**
//     * 查询用户权限信息
//     *
//     * @param currentUserId 当前用户ID
//     * @param adminUser     true管理平台用，false前端用户
//     * @return 权限LIST
//     */
//    private List<String> getUserPermissions(String currentUserId, Boolean adminUser) {
//        UserPermission userPermission = new UserPermission();
//        userPermission.setUserId(currentUserId);
//        userPermission.setAdminUser(adminUser);
//        return userPermission.sum();
//    }
//
//}
