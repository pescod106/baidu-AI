package com.ltar.ai.ocr.service.impl;

import com.ltar.ai.ocr.service.BaiDuAiOcrOptions;

import java.util.HashMap;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/21
 * @version: 1.0.0
 */
public class ReceiptOptionsImpl implements BaiDuAiOcrOptions {

    public HashMap<String, String> generateOptionMap() {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("probability", "true");
        options.put("accuracy", "normal");
        options.put("detect_direction", "true");
        return options;
    }
}
