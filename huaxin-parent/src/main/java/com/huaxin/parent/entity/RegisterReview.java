package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author cxks
 * @Date 2021/8/1 20:51
 */
@Data
@TableName("register_review")
@Accessors(chain = true)
public class RegisterReview {

    /**
     * MP:主键自增时 会将主键的数据动态的回显给对象
     */
    @TableId(type = IdType.AUTO)
    private int rrId;
    private String syQid;
    private String registerationUserCode;
    private String registerationUserAddress;
    private int registerationHandleProgress;
    private int registerationHandleResult;
    private Date registerationRequestTime;
    private Date registerationHandleTime;
    private String registerationHandle;
}
