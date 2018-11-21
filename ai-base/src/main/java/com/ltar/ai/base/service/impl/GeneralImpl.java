package com.ltar.ai.base.service.impl;

import com.ltar.base.util.StreamUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/19
 * @version: 1.0.0
 */
@Component
public class GeneralImpl extends AbstractBaiDuAiOcr {

    @Override
    public HashMap<String, String> generateOptionMap() {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("recognize_granularity", "big");
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");
        return options;
    }

    public JSONObject getResponse(String image) {
        return client.general(StreamUtils.fileToByte(image), generateOptionMap());
    }
}
