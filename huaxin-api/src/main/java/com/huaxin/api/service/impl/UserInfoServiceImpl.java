package com.huaxin.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huaxin.api.mapper.UserInfoMapper;
import com.huaxin.api.service.UserInfoService;
import com.huaxin.parent.entity.UserInfo;
import com.huaxin.parent.vo.JsonResult;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    private final String DEFAULT_PROFILE_PICTURE = "http://qwheo6bex.hn-bkt.clouddn.com/1.jpg"; // 默认头像地址

    @Override
    public JsonResult getUserInfo(int loginId) {

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.select("user_id").eq("login_id",loginId);
        UserInfo userInfo1 = userInfoMapper.selectOne(wrapper);

        if(ObjectUtils.isEmpty(userInfo1)) { // 表明是用户第一次查看，更新userInfo，并返回结果
            String inviteCode = "h" + new Date().getTime() + "x";
            String userTag = StringUtils.join(new String[]{"新用户"}); // 将数组转化为字符串
            UserInfo userInfo = new UserInfo()
                    .setLoginId(loginId)
                    .setInviteCode(inviteCode) // 用户邀请码
                    .setUserProfilePicture(DEFAULT_PROFILE_PICTURE) // 头像默认图片地址
                    .setUserTag(userTag); // 用户标签
            int row = userInfoMapper.insert(userInfo);
            if(row == 1) { // 用户信息更新成功
                UserInfo userInfo2 = userInfoMapper.selectOne(wrapper);
                return JsonResult.success(userInfo.setUserId(userInfo2.getUserId()));
            }
        }
        else { // 非第一次查看，则查询数据库信息
            int userId = userInfo1.getUserId();
            UserInfo userInfo = userInfoMapper.selectById(userId);
            if(!ObjectUtils.isEmpty(userInfo)) // 如果查询结果不为空
                return JsonResult.success(userInfo);

        }
        return JsonResult.fail("获取用户信息失败！");
    }
}
