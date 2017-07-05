package com.heitian.ssm.dao;

import com.heitian.ssm.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int insert(@Param("pojo") Role pojo);

    int insertSelective(@Param("pojo") Role pojo);

    int insertList(@Param("pojos") List<Role> pojo);

    int update(@Param("pojo") Role pojo);

    Role findByCode(String code);
}
