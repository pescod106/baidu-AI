package com.ltar.ai.base.service.impl;

import com.baidu.aip.ocr.AipOcr;
import com.ltar.ai.base.service.BaiDuAiOcr;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/19
 * @version: 1.0.0
 */
public abstract class AbstractBaiDuAiOcr implements BaiDuAiOcr {

    @Autowired
    protected AipOcr client;
    /**
     * 生成请求OCR请求参数
     *
     * @return
     */
    abstract HashMap<String, String> generateOptionMap();
}
