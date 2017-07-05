package com.heitian.ssm.service;

import com.heitian.ssm.model.User;

import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {
    int insert(User pojo);

    int insertSelective(User pojo);

    int insertList(List<User> pojos);

    int update(User pojo);

    List<User> findAllUser();

    User findUserByLoginName(String loginName);

}
