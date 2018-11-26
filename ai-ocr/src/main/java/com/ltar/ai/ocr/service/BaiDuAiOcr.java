package com.ltar.ai.ocr.service;

import com.ltar.framework.base.provider.ApplicationContextProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/21
 * @version: 1.0.0
 */
@Service
public class BaiDuAiOcr implements InitializingBean {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    /**
     * 存储所有百度OCR接口的实现类
     */
    private static Map<String, BaiduAipOcr> beanMap = null;

    public JSONObject requestAip(String ocrType, String imagePath) {
        return beanMap.get(ocrType).requestAip(imagePath);
    }

    public void afterPropertiesSet() throws Exception {
        beanMap = applicationContextProvider.getBeansOfType(BaiduAipOcr.class);
    }
}
