package com.heitian.ssm.model;


import com.heitian.ssm.arch.base.BaseDomain;
import lombok.Data;

import java.util.List;

/**
 * 角色
 */
@Data
public class Role extends BaseDomain {
    private String code;
    private String name;
    /**
     * 权限列表
     */
    private List<Module> moduleList;
}
