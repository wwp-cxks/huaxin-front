package com.huaxin.api.controller;

import com.huaxin.api.security.config.JwtSecurityConfig;
import com.huaxin.api.security.util.JwtTokenUtils;
import com.huaxin.api.security.util.SpringContextHolderUtils;
import com.huaxin.api.service.UserInfoService;
import com.huaxin.parent.annotation.UserLoginToken;
import com.huaxin.parent.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hx/userInfo")
@UserLoginToken
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    private final JwtTokenUtils jwtTokenUtils;

    public UserInfoController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    /**
     * loginid  23
     * eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eJwty0EKwjAQRuG7_OsEMpPp2OYEHsHtaANWF5YmpYXSuxvB5fvgHbC1PpEOzFbK9llGJHC83h77uxBHgcNa8tL0JzgdXnVqFUMUG4n83XjwQqzeWM2LDhZyR12wvr2TVSRSvij3KtEh7_MfVBqcX5t8Izk.Uonc1ZDxFlKc5IOVz1fwv3-M6M8xBR8uETday7JtYlCyx26LLLCqsEur6qi22YVyDMG5HiktciFuYE7QRxf4yA
     * Sat Jul 31 01:04:03 CST 2021 过期
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public JsonResult getUserInfo(HttpServletRequest request){
        JwtSecurityConfig jwtSecurityConfig = SpringContextHolderUtils.getBean(JwtSecurityConfig.class);
        String bearerToken = request.getHeader(jwtSecurityConfig.getHeader()); // 获取请求头中的授权信息
        String token = bearerToken.substring(jwtSecurityConfig.getTokenStartWith().length());
        int loginId = jwtTokenUtils.getIdFromToken(token); // 获取用户的登录id
        return userInfoService.getUserInfo(loginId);
    }
}
