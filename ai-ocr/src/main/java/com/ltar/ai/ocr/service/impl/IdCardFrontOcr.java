package com.ltar.ai.ocr.service.impl;

import com.ltar.ai.ocr.service.AbstractBaiduAipOcr;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @desc: 身份证识别-正面
 * @author: changzhigao
 * @date: 2018/11/27
 * @version: 1.0.0
 */
@Component
public class IdCardFrontOcr extends AbstractBaiduAipOcr {
    @Override
    protected ServiceCaller getServiceCaller() {
        return (image) -> {
            HashMap<String, String> options = new HashMap<>(2);
            options.put("detect_direction", "true");
            options.put("detect_risk", "false");
            return aipOcr.idcard(image, "front", options);

        };
    }
}
