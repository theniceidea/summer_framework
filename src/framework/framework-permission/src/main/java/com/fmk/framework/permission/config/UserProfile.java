//package com.fmk.framework.permission.config;
//
//import com.google.common.collect.Lists;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * @author 登录用户对象
// */
//public class UserProfile {
//
//    private String userId;
//    private String username;
//    private String realName;
//    private List<String> permissionCodes = Lists.newArrayList();
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Stream.concat(getPermissionCodes().stream(), Stream.of("USER"))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getRealName() {
//        return realName;
//    }
//
//    public void setRealName(String realName) {
//        this.realName = realName;
//    }
//
//    public List<String> getPermissionCodes() {
//        return permissionCodes;
//    }
//
//    public void setPermissionCodes(List<String> permissionCodes) {
//        this.permissionCodes = permissionCodes;
//    }
//}
