package com.fmk.framework.permission.entities;

import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daoannotations.Table;
import com.fmk.framework.entitiesbasic.IdTimeEntity;

import java.sql.Timestamp;

@Table("user_admin")
public class AdminUser extends IdTimeEntity {

    /**
     * loginId
     */
    @Column("login_id")
    private String loginId;
    public static final String _loginId = "login_id";

    /**
     * firstName
     */
    @Column("first_name")
    private String firstName;
    public static final String _firstName = "first_name";

    /**
     * lastName
     */
    @Column("last_name")
    private String lastName;
    public static final String _lastName = "last_name";

    /**
     * fullName
     */
    @Column("full_name")
    private String fullName;
    public static final String _fullName = "full_name";

    /**
     * nickName - 昵称
     */
    @Column("nick_name")
    private String nickName;
    public static final String _nickName = "nick_name";

    /**
     * password - 密码
     */
    @Column("password")
    private String password;
    public static final String _password = "password";

    /**
     * mobile - 手机号
     */
    @Column("mobile")
    private String mobile;
    public static final String _mobile = "mobile";

    /**
     * email - 邮箱
     */
    @Column("email")
    private String email;
    public static final String _email = "email";

    /**
     * avatar - 头像
     */
    @Column("avatar")
    private String avatar;
    public static final String _avatar = "avatar";

    /**
     * roleId - 角色id
     */
    @Column("role_id")
    private String roleId;
    public static final String _roleId = "role_id";

    /**
     * loginTime - 登陆时间
     */
    @Column("login_time")
    private Timestamp loginTime;
    public static final String _loginTime = "login_time";

    /**
     * loginIp - 登陆ip
     */
    @Column("login_ip")
    private String loginIp;
    public static final String _loginIp = "login_ip";

    /**
     * status - 用户状态（0-正常，1-已删除）
     */
    @Column("status")
    private Integer status;
    public static final String _status = "status";

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
