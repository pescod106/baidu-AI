package com.ltar.ai.optesthesia.ocr.service;

import com.baidu.aip.ocr.AipOcr;
import com.ltar.framework.base.util.StreamUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/26
 * @version: 1.0.0
 */
public abstract class AbstractBaiduAipOcr implements BaiduAipOcr {

    @Autowired
    protected AipOcr aipOcr;

    @Override
    public JSONObject requestAip(String image) {
        return getServiceCaller().requestAip(StreamUtils.fileToByte(image));
    }

    /**
     * 获取实现类
     *
     * @return
     */
    protected abstract ServiceCaller getServiceCaller();


    protected interface ServiceCaller {
        /**
         * 请求百度AipOcr接口
         *
         * @param image
         * @return
         */
        JSONObject requestAip(byte[] image);
    }
}
