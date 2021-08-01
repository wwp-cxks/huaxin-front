package com.huaxin.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: TODO(问卷详情（内容、浏览和回收情况等等）)
 * @Author cxks
 * @Date 2021/8/1 20:50
 */
@Data
@TableName("survey_detail")
@Accessors(chain = true)
public class SurveyDetail {

//    @TableId(type = IdType.AUTO)
//
}
