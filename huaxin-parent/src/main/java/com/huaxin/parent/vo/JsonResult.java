package com.huaxin.parent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author cxks
 * 通过SysResult对象 实现前后端数据交互的载体
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {

    /**
     * 如果后端服务器运行正常 返回200 否则返回201 表示失败
     */
    private Integer status;
    /**
     * 服务器返回提示
     */
    private String msg;
    /**
     * 服务器返回业务数据.
     */
    private Object data;

    public static JsonResult fail() {

        return new JsonResult(201, "服务器异常请求稍后", null);
    }

    public static JsonResult fail(String msg) {

        return new JsonResult(201, msg, null);
    }

    public static JsonResult success() {

        return new JsonResult(200, "服务器执行成功", null);
    }

    public static JsonResult success(Object data) {

        return new JsonResult(200, "服务器执行成功", data);
    }

    public static JsonResult success(String msg, Object data) {

        return new JsonResult(200, msg, data);
    }
}
