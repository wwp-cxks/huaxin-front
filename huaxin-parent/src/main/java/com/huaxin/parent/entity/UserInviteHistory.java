package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: TODO(用户邀请的历史记录)
 * @Author cxks
 * @Date 2021/8/1 20:49
 */
@Data
@TableName("user_invite_history")
@Accessors(chain = true)
public class UserInviteHistory {
}
