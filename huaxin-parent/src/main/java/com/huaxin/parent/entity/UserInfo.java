package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("user_info")
@Accessors(chain = true)
public class UserInfo {

    @TableId(type = IdType.AUTO) //MP:主键自增时 会将主键的数据动态的回显给对象
    private int userId;
    private int loginId;
    private Integer userGender; // 性别
    private String userProfilePicture; // 用户头像url
    private String userRealName; // 用户真实姓名
    private String userIdentityCard; // 用户身份证
    private String inviteCode; // 用户邀请码
    private String userTag; // 用户标签
}
