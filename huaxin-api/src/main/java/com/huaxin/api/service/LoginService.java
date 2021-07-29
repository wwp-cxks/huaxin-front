package com.huaxin.api.service;

import cn.hutool.http.server.HttpServerRequest;
import com.huaxin.parent.entity.Login;
import com.huaxin.parent.vo.JsonResult;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    JsonResult doRegister(Login login);

    JsonResult doLogin(Login login, HttpServletRequest request);

    JsonResult doRegisterByPhone(String phone);

    JsonResult doLoginByPhone(String phone, HttpServletRequest request);
}
