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

/**
 * @author cxks
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 默认头像地址
     */
    private final String DEFAULT_PROFILE_PICTURE =
            "http://qwheo6bex.hn-bkt.clouddn.com/1.jpg";


    @Override
    public JsonResult getUserInfo(int loginId) {

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.select("user_id").eq("login_id",loginId);
        UserInfo userInfo1 = userInfoMapper.selectOne(wrapper);

        // 表明是用户第一次查看，更新userInfo，并返回结果
        if(ObjectUtils.isEmpty(userInfo1)) {
            String inviteCode = "h" + System.currentTimeMillis() + "x";
            // 将数组转化为字符串
            String userTag = StringUtils.join(new String[]{"新用户"});
            UserInfo userInfo = new UserInfo()
                    .setLoginId(loginId)
                    // 用户邀请码
                    .setInviteCode(inviteCode)
                    // 头像默认图片地址
                    .setUserProfilePicture(DEFAULT_PROFILE_PICTURE)
                    // 用户标签
                    .setUserTag(userTag);
            int row = userInfoMapper.insert(userInfo);
            // 用户信息更新成功
            if(row == 1) {
                UserInfo userInfo2 = userInfoMapper.selectOne(wrapper);
                return JsonResult.success(userInfo.setUserId(userInfo2.getUserId()));
            }
        }
        else { // 非第一次查看，则查询数据库信息
            int userId = userInfo1.getUserId();
            UserInfo userInfo = userInfoMapper.selectById(userId);
            // 如果查询结果不为空
            if(!ObjectUtils.isEmpty(userInfo)) {
                return JsonResult.success(userInfo);
            }

        }
        return JsonResult.fail("获取用户信息失败！");
    }
}
