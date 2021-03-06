package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author cxks
 */
@Data
@TableName("login")
@Accessors(chain = true)
public class Login {

    /**
     * MP:主键自增时 会将主键的数据动态的回显给对象
     */
    @TableId(type = IdType.AUTO)
    private int loginId;
    private String loginName;
    private String loginPwd;
    private String loginPhone;
    private String loginSalt;
    private Date registerTime;
    private Date recentTime;
    private Integer loginStatus;
}
