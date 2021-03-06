package com.ltar.ai.optesthesia.ocr.service.impl;

import com.ltar.ai.optesthesia.ocr.service.AbstractBaiduAipOcr;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @desc: 银行卡识别
 * @author: changzhigao
 * @date: 2018/11/27
 * @version: 1.0.0
 */
@Component
public class BankCardOcr extends AbstractBaiduAipOcr {
    @Override
    protected ServiceCaller getServiceCaller() {
        return (image) -> {
            HashMap<String, String> options = new HashMap<>(0);
            return aipOcr.bankcard(image, options);
        };
    }
}
