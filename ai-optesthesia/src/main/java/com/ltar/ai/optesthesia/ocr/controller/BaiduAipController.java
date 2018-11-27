package com.ltar.ai.optesthesia.ocr.controller;

import com.alibaba.fastjson.JSONObject;
import com.ltar.ai.optesthesia.ocr.service.BaiDuAiOcr;
import com.ltar.framework.web.vo.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/26
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/aip")
public class BaiduAipController {

    @Autowired
    private BaiDuAiOcr baiDuAiOcr;

    @RequestMapping(value = "/ocr/{ocrType}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse imageOcr(@PathVariable(value = "ocrType") String ocrType,
                                 @RequestParam(value = "image") String image) {
        return new JsonResponse()
                .setData(JSONObject.parseObject(baiDuAiOcr.requestAip(ocrType, image).toString(), Map.class));
    }


}
