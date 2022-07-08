package com.fmk.framework.permission.entities;

import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daoannotations.Table;
import com.fmk.framework.entitiesbasic.IdTimeEntity;


@Table("user_role_acl")
public class UserRoleAcl extends IdTimeEntity {

    /**
     * rolesId - 角色id
     */
    @Column("roles_id")
    private String rolesId;
    public static final String _rolesId = "roles_id";
    /**
     * menusJson - 菜单权限
     */
    @Column("menus_json")
    private String menusJson;
    public static final String _menusJson = "menus_json";

    /**
     * rightsJson - 按钮权限
     */
    @Column("rights_json")
    private String rightsJson;
    public static final String _rightsJson = "rights_json";

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getMenusJson() {
        return menusJson;
    }

    public void setMenusJson(String menusJson) {
        this.menusJson = menusJson;
    }

    public String getRightsJson() {
        return rightsJson;
    }

    public void setRightsJson(String rightsJson) {
        this.rightsJson = rightsJson;
    }

}
