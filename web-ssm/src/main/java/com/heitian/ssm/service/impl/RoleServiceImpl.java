package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.RoleDao;
import com.heitian.ssm.model.Role;
import com.heitian.ssm.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    public int insert(Role pojo){
        return roleDao.insert(pojo);
    }

    public int insertSelective(Role pojo){
        return roleDao.insertSelective(pojo);
    }

    public int insertList(List<Role> pojos){
        return roleDao.insertList(pojos);
    }

    public int update(Role pojo){
        return roleDao.update(pojo);
    }

    @Override
    public Role findByCode(String code) {
        return roleDao.findByCode(code);
    }
}
