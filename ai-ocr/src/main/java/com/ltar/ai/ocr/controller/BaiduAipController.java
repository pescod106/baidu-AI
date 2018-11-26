package com.ltar.ai.ocr.controller;

import com.ltar.ai.ocr.service.BaiDuAiOcr;
import com.ltar.framework.web.vo.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JsonResponse imageOcr(@PathVariable(value = "ocrType") String ocrType, String image) {
        return new JsonResponse().set("result", baiDuAiOcr.requestAip(ocrType, image));
    }
}
