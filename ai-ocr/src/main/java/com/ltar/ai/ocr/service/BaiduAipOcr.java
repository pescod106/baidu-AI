package com.ltar.ai.ocr.service;

import org.json.JSONObject;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/26
 * @version: 1.0.0
 */
public interface BaiduAipOcr {
    /**
     * 请求百度AipOcr接口
     *
     * @param image
     * @return
     */
    JSONObject requestAip(String image);
}
