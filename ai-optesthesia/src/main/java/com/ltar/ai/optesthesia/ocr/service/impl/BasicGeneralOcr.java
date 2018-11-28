package com.ltar.ai.optesthesia.ocr.service.impl;

import com.ltar.ai.optesthesia.ocr.service.AbstractBaiduAipOcr;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @desc: 通用文字识别
 * @author: changzhigao
 * @date: 2018/11/26
 * @version: 1.0.0
 */
@Component
public class BasicGeneralOcr extends AbstractBaiduAipOcr {

    @Override
    protected ServiceCaller getServiceCaller() {
        return (image) -> {
            HashMap<String, String> options = new HashMap<String, String>(4);
            options.put("language_type", "CHN_ENG");
            options.put("detect_direction", "true");
            options.put("detect_language", "true");
            options.put("probability", "true");
           return aipOcr.basicGeneral(image, options);
        };
    }
}
