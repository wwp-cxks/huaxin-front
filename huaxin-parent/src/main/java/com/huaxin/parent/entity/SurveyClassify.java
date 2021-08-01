package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description: TODO(问卷分类)
 * @Author cxks
 * @Date 2021/8/1 20:50
 */
@Data
@TableName("survey_classify")
@Accessors(chain = true)
public class SurveyClassify {

    /**
     * MP:主键自增时 会将主键的数据动态的回显给对象
     */
    @TableId(type = IdType.AUTO)
    private int syTypeId;
    private String syType;
    private int syTypeNum;
    private Date syCreatedTime;
}
