package com.huaxin.api.controller;

import com.huaxin.api.security.util.JwtTokenUtils;
import com.huaxin.api.service.LoginService;
import com.huaxin.parent.entity.Login;
import com.huaxin.parent.util.IpAndAddressUtils;
import com.huaxin.parent.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hx/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    private final JwtTokenUtils jwtTokenUtils;
    public LoginController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    /**
     * 用户名和密码注册
     * @param login
     * @return
     */
    @PostMapping("/register")
    public JsonResult doRegisterByNameAndPwd(Login login, HttpServletRequest request){
        JsonResult jsonResult = loginService.doRegister(login);

        if(jsonResult.getStatus() == 200) {
            jsonResult.setData(mapToToken(login,request));
        }
       return jsonResult;
    }

    /**
     * 使用电话号码和验证码 来进行身份验证和注册
     * @param phone 电话
     * @param code 验证码
     * @return
     */
    @PostMapping("/registerByPhone")
    public JsonResult registerByPhone(String phone,String code, HttpServletRequest request){
        System.out.println("phone  " + phone + "   code  " + code);
        if("123".equals(code)) {
            JsonResult jsonResult = loginService.doRegisterByPhone(phone);
            if(jsonResult.getStatus() == 200){
                Login login = (Login) jsonResult.getData();
                jsonResult.setData(mapToToken(login,request));
            }
            return jsonResult;
        }
        return JsonResult.fail("验证码输入错误！");
    }

    /**
     * 使用用户名和密码登录
     * @param login
     * @return
     */
    @PostMapping
    public JsonResult doLogin(Login login, HttpServletRequest request){
        System.out.println(login);
        JsonResult jsonResult = loginService.doLogin(login);

//        System.out.println(jsonResult);
        if(jsonResult.getStatus() == 200) {
            jsonResult.setData(mapToToken(login,request));
        }
        return jsonResult;
    }

    /**
     * 使用电话号码和验证码登录账号
     * @param phone
     * @param code
     * @return
     */
    @PostMapping("/loginByPhone")
    public JsonResult doLoginByPhone(String phone,String code, HttpServletRequest request){
        if("123456".equals(code)) {
            JsonResult jsonResult = loginService.doLoginByPhone(phone);;
            if(jsonResult.getStatus() == 200){
                Login login = (Login) jsonResult.getData();
                jsonResult.setData(mapToToken(login,request));
            }
            return jsonResult;
        }
        return JsonResult.fail("验证码输入错误！");
    }

    /**
     * 将用户名和密码用jwt包装起来，返回token
     * @param login
     * @return
     */
    public String mapToToken(Login login, HttpServletRequest request){
        String userName = login.getLoginName();
        String password = login.getLoginPwd();
        int loginId = login.getLoginId();

        if(login.getLoginId() <= 0 ) // login中loginId不存在，则重新查询
           loginId = loginService.getLoginId(login);

        Map map = new HashMap();
        map.put("user", userName);
        map.put("password", loginId + "HX" + password);

        log.info("map：{}",map);

        String ip = IpAndAddressUtils.getIpAddr(request);
        String address = IpAndAddressUtils.getRealAddressByIP(ip);
        log.info( "ip为" + ip + "的用户" + userName + "在" + address + "登录" );

        return jwtTokenUtils.createToken(map);
    }
}
