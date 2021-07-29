package com.huaxin.api.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.huaxin.api.service.LoginService;
import com.huaxin.parent.entity.Login;
import com.huaxin.parent.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hx/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户名和密码注册
     * @param login
     * @return
     */
    @PostMapping("/register")
    public JsonResult doRegisterByNameAndPwd(Login login){
       return loginService.doRegister(login);
    }

    /**
     * 使用电话号码和验证码 来进行身份验证和注册
     * @param phone 电话
     * @param code 验证码
     * @return
     */
    @PostMapping("/registerByPhone")
    public JsonResult registerByPhone(String phone,String code){
        System.out.println("phone  " + phone + "   code  " + code);
        if("123".equals(code))
            return loginService.doRegisterByPhone(phone);
        return JsonResult.fail("验证码输入错误！");
    }

    /**
     * 使用用户名和密码登录
     * @param login
     * @return
     */
    @PostMapping
    public JsonResult doLogin(Login login, HttpServletRequest request){
        return loginService.doLogin(login, request);
    }

    /**
     * 使用电话号码和验证码登录账号
     * @param phone
     * @param code
     * @return
     */
    @PostMapping("/loginByPhone")
    public JsonResult doLoginByPhone(String phone,String code, HttpServletRequest request){
        if("123456".equals(code))
            return loginService.doLoginByPhone(phone,request);
        return JsonResult.fail("验证码输入错误！");
    }
}
