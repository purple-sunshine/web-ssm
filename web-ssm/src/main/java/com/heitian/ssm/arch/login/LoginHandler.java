package com.heitian.ssm.arch.login;


import com.heitian.ssm.arch.utils.QMap;
import com.heitian.ssm.model.Role;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.RoleService;
import com.heitian.ssm.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 功能：
 * Author:Yang yanli
 */
@Controller
@RequestMapping("/loginHandler")
public class LoginHandler {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;


    @RequestMapping("/forwardLogin")
    public String loginPage() {
        return "/login";
    }

    /**
     * 登陆
     *
     * @return
     * @Author:lhf
     */
    @ResponseBody
    @RequestMapping("/loginInfo")
    public QMap loginInfo(@RequestParam(value = "loginName") String loginName, @RequestParam(value = "password") String password, @RequestParam(value = "identifyingCode") String identifyingCode, HttpServletRequest request) {
        QMap qMap = new QMap();
        Integer tryLoginNum = (Integer) request.getSession().getAttribute("tryLoginNum");
        if (tryLoginNum == null) {
            tryLoginNum = 1;
        }
        String code = (String) request.getSession().getAttribute("verify-code-seesion-key");
        try {
            if (StringUtils.isNotEmpty(loginName)) {
                if (tryLoginNum < 3 || (tryLoginNum > 2 && identifyingCode.equalsIgnoreCase(code))) {
               /*if (StringUtils.isEmpty(identifyingCode)) {
                    qMap.setSuccess(false);
                    qMap.setMessage("验证码不能为空！");
                } else if (!identifyingCode.equalsIgnoreCase(code)) {
                    qMap.setSuccess(false);
                    qMap.setMessage("验证码不正确！");
                } else {*/
                    User user = userService.findUserByLoginName(loginName);
                    if (null == user) {
                        qMap.setSuccess(false);
                        qMap.setMessage("没有此用户或者用户名错误！");
                    } else {
                        String pwd = DigestUtils.md5Hex(password);
                        if (!pwd.equals(user.getPassword())) {
                            qMap.setSuccess(false);
                            qMap.setMessage("密码错误！");
                        } else {
                            Principal principal = new Principal();
                            principal.setUser(user);
                            List<String> codeList = new ArrayList<>();
                            List<String> nameList = new ArrayList<>();
                            if (StringUtils.isNotEmpty(user.getRoleCode())) {
                                Role role = roleService.findByCode(user.getRoleCode());
                                if (role != null) {
                                    for (int i = 0; i < role.getModuleList().size(); i++) {
                                        codeList.add(role.getModuleList().get(i).getCode());
                                        nameList.add(role.getModuleList().get(i).getName());
                                    }
                                }
                                principal.setModuleCodeList(codeList);
                                principal.setModuleNameList(nameList);
                                request.getSession().setAttribute("principal", principal);
                                request.getSession().removeAttribute("tryLoginNum");
                                qMap.setSuccess(true);
                            }
                        }
                    }
                    tryLoginNum++;
                    request.getSession().setAttribute("tryLoginNum", tryLoginNum);
                    qMap.add("tryLoginNum", tryLoginNum);
                } else {
                    qMap.setSuccess(false);
                    qMap.setMessage("验证码错误!");
                }
            } else {
                qMap.setSuccess(false);
                qMap.setMessage("用户名或密码不能为空！");
            }
        } catch (Exception e) {
            qMap.setSuccess(false);
            qMap.setMessage("系统异常!");
            e.printStackTrace();
        }
        //}
        return qMap;

    }

    /**
     * 退出返回首页
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/logoutSystem")
    public QMap logoutSystem(HttpServletRequest request) {
        QMap rtmMap = new QMap();
        try {
            request.getSession().removeAttribute("principal");
            request.getSession().removeAttribute("tryLoginNum");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtmMap;
    }

    /**
     * 返回首页
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param oldPwd
     * @param newPwd
     * @return
     * @Author:lhf
     */
    @ResponseBody
    @RequestMapping("/modifyPwd")
    public QMap modifyPwd(HttpServletRequest request, @RequestParam(value = "oldPwd") String oldPwd, @RequestParam(value = "newPwd") String newPwd) {
        QMap qMap = new QMap();
        try {
            Principal principal = (Principal) request.getSession().getAttribute("principal");
            User user = principal.getUser();
            if (StringUtils.isNotEmpty(oldPwd) && user.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
                String pwd = DigestUtils.md5Hex(newPwd);
                user.setPassword(pwd);
                user.setUpdateTime(new Date());
                userService.update(user);
                qMap.setSuccess(true);
                qMap.setMessage("修改密码成功，请重新登陆！");
                request.getSession().removeAttribute("principal");
                request.getSession().removeAttribute("tryLoginNum");
            } else {
                qMap.setSuccess(false);
                qMap.setMessage("请正确输入旧密码！");
            }

        } catch (Exception e) {
            qMap.setSuccess(false);
            qMap.setMessage("修改密码失败！");
            e.printStackTrace();
        }
        return qMap;
    }

    @RequestMapping("/signPage")
    public  String signPage(){
        return "/account_about";
    }

    @ResponseBody
    @RequestMapping("/signAccout")
    public QMap signAccout(User user) {
        QMap resultMap = new QMap();
        int i = userService.insert(user);
        resultMap.setMessage("保存成功！");
        resultMap.setSuccess(true);
        return resultMap;
    }

}
