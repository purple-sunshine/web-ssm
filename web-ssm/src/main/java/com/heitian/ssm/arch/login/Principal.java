package com.heitian.ssm.arch.login;


import com.heitian.ssm.model.Role;
import com.heitian.ssm.model.User;
import lombok.Data;

import java.util.List;

/**
 * 功能:会话容器 seesionContainer
 */
@Data
public class Principal{

    private User user;
    private Role role;
    private List<String> moduleCodeList;
    private List<String> moduleNameList;
}
