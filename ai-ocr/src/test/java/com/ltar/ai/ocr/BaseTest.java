package com.ltar.ai.ocr;

import com.ltar.ai.ocr.service.BaiDuAiOcr;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/19
 * @version: 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml"})
public class BaseTest {

    @Autowired
    private BaiDuAiOcr baiDuAiOcr;

    @Test
    public void testClient() {
        String imgPath = "/Users/pescod/Desktop/time.png";
        JSONObject jsonObject = baiDuAiOcr.requestAip("generalOcr", imgPath);
        System.out.println(jsonObject);
    }

    public static byte[] getImgStr(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
