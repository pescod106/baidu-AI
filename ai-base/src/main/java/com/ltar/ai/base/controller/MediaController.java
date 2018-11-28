package com.ltar.ai.base.controller;

import com.ltar.framework.web.vo.JsonResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @desc:
 * @author: changzhigao
 * @date: 2018/11/28
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/aip")
public class MediaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);

    @Value("${aip.file.path}")
    private String mediaFilePath;

    @RequestMapping(value = "/upload/{mediaType}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse upload(@PathVariable(value = "mediaType") String mediaType, @RequestParam(value = "media") MultipartFile media) {
        //获取文件的原始文件名
        String originFileName = media.getOriginalFilename();
        //保存到服务器的文件名
        String saveFileName = System.currentTimeMillis() + "-" + originFileName;
        //todo 每个文件类型建立一个文件夹
        //获取当前时间
        String currentDateStr = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String filePath = mediaFilePath + File.separator
                + mediaType + File.separator
                + currentDateStr + File.separator
                + saveFileName;
        File mediaFile = new File(filePath);
        if (!mediaFile.getParentFile().exists() || !mediaFile.getParentFile().isDirectory()) {
            mediaFile.getParentFile().mkdir();
        }
        try {
            mediaFile.createNewFile();
            FileUtils.writeByteArrayToFile(mediaFile, IOUtils.toByteArray(media.getInputStream()));
            LOGGER.info("save file as:{}, fileSize:{}", mediaFile.getAbsoluteFile(), mediaFile.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonResponse().set("fileUrl", mediaFile.getAbsoluteFile());
    }
}
