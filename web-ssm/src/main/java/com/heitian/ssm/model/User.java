package com.heitian.ssm.model;


import com.heitian.ssm.arch.base.BaseDomain;
import lombok.Data;

/**
 * 用户
 */
@Data
public class User extends BaseDomain {
    /** 登录使用*/
    private String loginName;
    /**显示使用*/
    private String userName;
    /**角色代码*/
    private String roleCode;
    /**密码md5加密*/
    private String password;
    /**联系方式*/
    private String phone;
    /** 部门*/
    private String departmentCode;
    /**性别*/
    private String sex;
    /**备注*/
    private String note;

    /**辅助字段*/
    //部门名称
    private String departmentName;
    //角色名称
    private String roleName;
}
