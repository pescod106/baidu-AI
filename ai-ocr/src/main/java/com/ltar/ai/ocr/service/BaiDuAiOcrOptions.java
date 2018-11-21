package com.ltar.ai.ocr.service;


import java.util.HashMap;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/19
 * @version: 1.0.0
 */
public interface BaiDuAiOcrOptions {

    /**
     * 生成请求OCR请求参数
     *
     * @return
     */
    HashMap<String, String> generateOptionMap();
}
