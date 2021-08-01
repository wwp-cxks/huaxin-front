package com.huaxin.api.service;

import com.huaxin.parent.entity.Login;
import com.huaxin.parent.vo.JsonResult;

/**
 * @author cxks
 */
public interface LoginService {

    /**
     * 注册
     * @param login
     * @return
     */
    JsonResult doRegister(Login login);

    /**
     *
     * @param login
     * @return
     */
    JsonResult doLogin(Login login);

    /**
     * 电话注册
     * @param phone
     * @return
     */
    JsonResult doRegisterByPhone(String phone);

    /**
     * 电话号码登录
     * @param phone
     * @return
     */
    JsonResult doLoginByPhone(String phone);


    /**
     * 用户名、密码登录
     * @param login
     * @return
     */
    int getLoginId(Login login);
}
