package com.huaxin.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaxin.api.mapper.LoginMapper;
import com.huaxin.api.service.LoginService;
import com.huaxin.parent.entity.Login;
import com.huaxin.parent.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    private final String DEFAULT_PWD = "123456"; // 默认初始密码为"123456"
    private final Integer DEFAULT_STATUS = 1; // 普通用户

    /**
     * 使用用户名、密码注册
     * @param login
     * @return
     */
    @Override
    public JsonResult doRegister(Login login) {
        String userName = login.getLoginName();
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",userName);
        List<Login> list = loginMapper.selectList(wrapper);

        if(CollectionUtils.isEmpty(list)) { // 判断集合是否为空,不为空，则该用户已存在

            String password = login.getLoginPwd();
            String salt = UUID.randomUUID().toString();
            SimpleHash simpleHash = new SimpleHash("MD5",password,salt,3);
            password = simpleHash.toHex();

            login.setLoginSalt(salt)
                    .setLoginPwd(password)
                    .setLoginStatus(DEFAULT_STATUS) // 普通用户
                    .setRegisterTime(new Date()); // 设置注册时间

            int row = loginMapper.insert(login);
            if(row == 1)
                return JsonResult.success("注册成功",login);
            else
                return JsonResult.fail("注册失败！请稍后重试");
        }
        return JsonResult.fail("该用户名已存在！");
    }

    /**
     * 使用电话号码进行注册
     * @param phone
     * @return
     */
    @Override
    public JsonResult doRegisterByPhone(String phone) {
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.eq("login_phone",phone);
        List<Login> list = loginMapper.selectList(wrapper);

        if(CollectionUtils.isEmpty(list)) {
            Date currentTime = new Date();
            Login login = new Login()
                    .setLoginStatus(DEFAULT_STATUS)
                    .setLoginPhone(phone)
                    .setRegisterTime(currentTime)
                    .setRecentTime(currentTime)
                    .setLoginPwd(DEFAULT_PWD); // 电话号码注册统一规定密码为123456
           int row = loginMapper.insert(login);
           if(row == 1)
               return JsonResult.success("注册成功！",login);
           else
               return JsonResult.fail("注册失败！请稍后重试");
        }
        // 如果list不为空，则该电话号码已被注册
        return JsonResult.fail("该号码已被注册！");
    }

    /**
     * 手机号登录
     * @param phone
     * @return
     */
    @Override
    public JsonResult doLoginByPhone(String phone) {
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.eq("login_phone",phone);
        Login login = loginMapper.selectOne(wrapper);
        if(ObjectUtils.isEmpty(login))
            return JsonResult.fail("验证失败！");

        Date currentTime = new Date();
        int row = loginMapper.update(new Login().setRecentTime(currentTime), wrapper); // 更新用户登录时间
        if (row != 1)
            log.error("登录时间更新异常！");

        return JsonResult.success("欢迎回来！",login);
    }

    /**
     * 获取用户的id信息
     * @param login
     * @return
     */
    @Override
    public int getLoginId(Login login) {
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.select("login_id")
                .eq("login_name",login.getLoginName());
        return loginMapper.selectOne(wrapper).getLoginId();
    }

    /**
     * 用户名和密码登录
     * @param login
     * @return
     */
    @Override
    public JsonResult doLogin(Login login) {
        QueryWrapper<Login> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",login.getLoginName());
        Login list = loginMapper.selectOne(wrapper);
        if(ObjectUtils.isEmpty(list)) // 说明当前用户不存在
            return JsonResult.fail("用户名或密码输入错误！");
        // 否则
        String password = login.getLoginPwd();
        String salt = list.getLoginSalt();
        SimpleHash simpleHash = new SimpleHash("MD5",password,salt,3);
        password = simpleHash.toHex();
        List<Login> loginList = loginMapper.selectList(wrapper.eq("login_pwd",password));

        if(CollectionUtils.isEmpty(loginList)){ // 用户名或密码输入错误
            return JsonResult.fail("用户名或密码输入错误！");
        }
        else {
            Date currentTime = new Date();
            int row = loginMapper.update(new Login().setRecentTime(currentTime), wrapper); // 更新用户登录时间
            if (row != 1)
                log.error("登录时间更新异常！");

            return JsonResult.success("欢迎回来，" + login.getLoginName(),null);
        }
    }


}
