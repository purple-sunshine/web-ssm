package com.heitian.ssm.service;

import com.heitian.ssm.model.Role;

import java.util.List;

/**
 * Created by xiang on 2017/6/20.
 */
public interface RoleService {
    public int insert(Role pojo);

    public int insertSelective(Role pojo);

    public int insertList(List<Role> pojos);

    public int update(Role pojo);

    Role findByCode(String code);
}
