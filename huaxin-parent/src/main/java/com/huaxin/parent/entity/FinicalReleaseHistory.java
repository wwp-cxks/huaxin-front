package com.huaxin.parent.entity;

import cn.hutool.db.meta.TableType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description: TODO(资金发放记录)
 * @Author cxks
 * @Date 2021/8/1 20:51
 */
@Data
@TableName("finical_release_history")
@Accessors(chain = true)
public class FinicalReleaseHistory {

    /**
     * MP:主键自增时 会将主键的数据动态的回显给对象
     */
    @TableId(type = IdType.AUTO)
    private int frId;
    private String frObject;
    private String frReason;
    private int count;
    private Date frTime;
    private String frOperator;
    private int frProgress;
}
