package com.aster.bcu.printroom.tools;

import com.aster.bcu.printroom.service.impl.PrintFileServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class GlobalFileUtils {




    public static Map saveFileToTempDir(String basePath, String serverUrl, MultipartFile[] multipartFile) throws IOException {
        System.out.println(serverUrl);
        Map<String,Object> result=new HashMap<>();
        StringBuilder filesNames = new StringBuilder();
        if (multipartFile.length>0) {
            for (MultipartFile file : multipartFile) {
                String format= file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
                String fileName= UUID.randomUUID().toString()+"."+format;
                String dir=basePath+"/";
                File f=new File(dir);
                if (!f.exists()) {
                    f.mkdirs();
                }
                File saveFile = new File(dir+fileName);
                file.transferTo(saveFile);
                filesNames.append(serverUrl+"/"+fileName);
                filesNames.append(";");
                System.out.println("File saved path:"+dir+fileName);

                String contentType = file.getContentType();


                result.put("name",filesNames.toString());


                switch (contentType){
                    case "image/jpeg":
                    case "image/png":
                        result.put("pages",1);
                        break;
                    case "application/vnd.openxmlformats-officedocument.wordprocessingml.document"://docx
                        XWPFDocument wordDocx = new XWPFDocument (new FileInputStream(saveFile));
                        result.put("pages",wordDocx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages());
                        break;
                    case "application/msword"://doc
                        HWPFDocument wordDoc = new HWPFDocument (new FileInputStream(saveFile));
                        result.put("pages", wordDoc.getSummaryInformation().getPageCount());
                        break;
                    case "application/pdf":
                        PDDocument pdf = PDDocument.load(new FileInputStream(saveFile));
                        result.put("pages",pdf.getNumberOfPages());
                        break;
                    default: result.put("pages",-1);
                }


                //PrintFileServiceImpl printFileService=new PrintFileServiceImpl();
                //printFileService.printJPG("",saveFile,"HP DeskJet 3630 series");

            }
            return result;
        }else {
            throw new RuntimeException("文件不存在");
        }

    }


    /**
    * @Author: Wls
    * @Date: 11:34 2020/4/17
    * @Description: 未完成
    */
    public static boolean moveFileToServiceDir(String baseDir,String serviceDir,String fileNames){
        String tempDir= baseDir + "/temp/";
        String targetDir= baseDir + "/" + serviceDir + "/";

        for (String fileName : fileNames.split(";")) {
            File file=new File(tempDir+fileName);
            if (file.exists()) {
                 file.renameTo(new File(targetDir + fileName));
            }

        }
        return false;
    }

}
