package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cxks
 */
@Data
@TableName("user_info")
@Accessors(chain = true)
public class UserInfo {

    /**
     * MP:主键自增时 会将主键的数据动态的回显给对象
     */
    @TableId(type = IdType.AUTO)
    private int userId;
    private int loginId;
    private Integer userGender;

    /**
     *  用户头像url
     */
    private String userProfilePicture;

    /**
     * 用户真实姓名
      */
    private String userRealName;

    /**
     * 用户身份证
     */
    private String userIdentityCard;

    /**
     * 用户邀请码
     */
    private String inviteCode;

    /**
     * 用户标签
     */
    private String userTag;
}
