package com.ltar.ai.ocr.service;

import com.baidu.aip.ocr.AipOcr;
import com.ltar.ai.ocr.service.impl.BasicGeneralOptionsImpl;
import com.ltar.ai.ocr.service.impl.GeneralOptionsImpl;
import com.ltar.ai.ocr.service.impl.ReceiptOptionsImpl;
import com.ltar.ai.ocr.service.impl.VatInvoiceOptionsImpl;
import com.ltar.base.util.StreamUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/21
 * @version: 1.0.0
 */
@Service
public class BaiDuAiOcr {

    @Autowired
    private AipOcr aipOcr;

    /**
     * 通用文字识别接口
     * 用户向服务请求识别某张图中的所有文字
     *
     * @param image
     * @return
     */
    public JSONObject basicGeneral(String image) {
        return aipOcr.basicGeneral(
                StreamUtils.fileToByte(image),
                (new BasicGeneralOptionsImpl()).generateOptionMap()
        );
    }

    public JSONObject basicAccurateGeneral(String image) {
        return aipOcr.basicAccurateGeneral(
                StreamUtils.fileToByte(image),
                (new BasicGeneralOptionsImpl()).generateOptionMap()
        );
    }

    public JSONObject general(String image){
        return aipOcr.general(
                StreamUtils.fileToByte(image),
                (new GeneralOptionsImpl()).generateOptionMap()
        );
    }

    public JSONObject receipt(String image) {
        return aipOcr.receipt(
                StreamUtils.fileToByte(image),
                (new ReceiptOptionsImpl()).generateOptionMap()
        );
    }

    public JSONObject vatInvoice(String image){
        return aipOcr.vatInvoice(
                StreamUtils.fileToByte(image),
                (new VatInvoiceOptionsImpl()).generateOptionMap()
        );
    }


}
