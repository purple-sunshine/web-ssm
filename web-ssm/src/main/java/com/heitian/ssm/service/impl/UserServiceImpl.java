package com.heitian.ssm.service.impl;

import com.heitian.ssm.arch.utils.Identities;
import com.heitian.ssm.dao.UserDao;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public int insert(User pojo) {
        String pwd = DigestUtils.md5Hex(pojo.getPassword());
        pojo.setId(Identities.uuid());
        pojo.setPassword(pwd);
        pojo.setRoleCode("2");
        pojo.setRoleName("普通博主");
        pojo.setCreateTime(new Date());
        return userDao.insert(pojo);
    }

    public int insertSelective(User pojo) {
        return userDao.insertSelective(pojo);
    }

    public int insertList(List<User> pojos) {
        return userDao.insertList(pojos);
    }

    public int update(User pojo) {
        return userDao.update(pojo);
    }

    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public User findUserByLoginName(String loginName) {
        return userDao.findUserByloginName(loginName);
    }
}
