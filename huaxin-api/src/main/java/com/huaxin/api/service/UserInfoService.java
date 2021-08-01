package com.huaxin.api.service;

import com.huaxin.parent.vo.JsonResult;

/**
 * @author cxks
 */
public interface UserInfoService {
    /**
     * 获取用户信息
     * @param loginId
     * @return
     */
    JsonResult getUserInfo(int loginId);
}
