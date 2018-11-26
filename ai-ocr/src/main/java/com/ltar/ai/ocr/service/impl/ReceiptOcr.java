package com.ltar.ai.ocr.service.impl;

import com.ltar.ai.ocr.service.AbstractBaiduAipOcr;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/26
 * @version: 1.0.0
 */
@Component
public class ReceiptOcr extends AbstractBaiduAipOcr {

    @Override
    protected ServiceCaller getServiceCaller() {
        return (image) -> {
            HashMap<String, String> options = new HashMap<String, String>(4);
            options.put("recognize_granularity", "big");
            options.put("probability", "true");
            options.put("accuracy", "normal");
            options.put("detect_direction", "true");
            return aipOcr.receipt(image, options);
        };
    }
}
