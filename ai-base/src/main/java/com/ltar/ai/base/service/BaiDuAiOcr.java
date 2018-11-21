package com.ltar.ai.base.service;

import org.json.JSONObject;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/19
 * @version: 1.0.0
 */
public interface BaiDuAiOcr {

    /**
     * 请求百度AI接口
     *
     * @param image
     * @return
     */
    JSONObject getResponse(String image);
}
