package com.fmk.framework.permission.entities;

import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daoannotations.Table;
import com.fmk.framework.entitiesbasic.IdTimeEntity;

import java.sql.Timestamp;

@Table("user_user")
public class User extends IdTimeEntity {

    /**
     *   用户类型   是否是会员
     *   1  普通用户   2  会员
     */
    @Column("user_type")
    private Integer userType;

    /**
     *  第一次用户关联授权的渠道编码
     */
    @Column("first_source_code")
    private String firstSourceCode;

    /**
     * loginId
     */
    @Column("login_id")
    private String loginId;

    /**
     * firstName
     */
    @Column("first_name")
    private String firstName;

    /**
     *   lastName
     */
    @Column("last_name")
    private String lastName;

    /**
     *   fullName
     */
    @Column("full_name")
    private String fullName;

    /**
     *  nickName - 昵称
     */
    @Column("nick_name")
    private String nickName;

    /**
     *  password - 密码
     */
    @Column("password")
    private String password;

    /**
     *  mobile - 手机号
     */
    @Column("mobile")
    private String mobile;

    /**
     * area_code - 区号
     */
    @Column("area_code")
    private String areaCode;
    public static final String _areaCode = "area_code";

    /**
     *   email - 邮箱
     */
    @Column("email")
    private String email;

    /**
     *  rank - 等级
     */
    @Column("rank")
    private Integer rank;
    /**
     *  avatar - 头像
     */
    @Column("avatar")
    private String avatar;

    /**
     *  externalId - 导入用户外部系统id
     */
    @Column("external_id")
    private String externalId;

    /**
     *  loginTime - 登陆时间
     */
    @Column("login_time")
    private Timestamp loginTime;

    /**
     *  loginIp - 登陆ip
     */
    @Column("login_ip")
    private String loginIp;

    /**
     *    status - 用户状态（0-正常，1-已删除）
     */
    @Column("status")
    private Integer status;

    /**
     * 是否同意条款 （1 同意  0 不同意）
     */
    @Column("agree_to_terms")
    private Integer agreeToTerms;

    /**
     *   推荐人id
     */
    @Column("reference_user_id")
    private String referenceUserId;

    /**
     * 兴趣
     */
    @Column("hobby")
    private String hobby;
    /**
     * 职业
     */
    @Column("job")
    private String job;

    /**
     * 性别
     */
    @Column("sex")
    private Integer sex;

    /**
     * 生日
     */
    @Column("birthday")
    private String birthday;

    /**
     * 区域
     */
    @Column("area")
    private String area;


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getFirstSourceCode() {
        return firstSourceCode;
    }

    public void setFirstSourceCode(String firstSourceCode) {
        this.firstSourceCode = firstSourceCode;
    }

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public Integer getAgreeToTerms() {
        return agreeToTerms;
    }

    public void setAgreeToTerms(Integer agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    public String getReferenceUserId() {
        return referenceUserId;
    }

    public void setReferenceUserId(String referenceUserId) {
        this.referenceUserId = referenceUserId;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
