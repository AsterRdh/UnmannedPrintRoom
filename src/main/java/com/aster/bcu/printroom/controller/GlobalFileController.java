package com.aster.bcu.printroom.controller;



import com.aster.bcu.printroom.config.GlobalConfig;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.entity.TaskInfo;
import com.aster.bcu.printroom.service.PrintFileService;
import com.aster.bcu.printroom.tools.GlobalFileUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/file")
public class GlobalFileController {

    @Resource
    private GlobalConfig config;
    private Random random=new Random();
    private Map<String,String> fileMap=new HashMap<>();
    private Map<Integer,Queue<TaskInfo>> taskQueue=new HashMap<>();
    @Resource
    private PrintFileService printFileService;

    @GetMapping("/test")
    public Message testContions(){
        return Message.success("test");
    }

    @PostMapping("/")
    public Message fileUpload(int printStationCode,MultipartFile[] file,String fileName) throws IOException {
        if (file.length>0) {
            Map<String,Object> remap=new HashMap<>();
            String contentType = file[0].getContentType();
            Map rs= GlobalFileUtils.saveFileToTempDir(config.globalFilePath,config.serverUrl,file);
            System.out.println("name: "+file[0].getOriginalFilename());
            StringBuffer codeString=new StringBuffer();
            for(int i=0;i<6;i++)
                codeString.append(random.nextInt(10));
            addtoPrintQueue(printStationCode,((String)rs.get("name")).replace(";",""),"a",codeString.toString());


            //Message message=printFileService.printJPG(rs,file[0],"HP DeskJet 3630 series");


            Message<Object> success = Message.success(null);
            success.setObj(rs);
            return success;
        }
        return Message.fail("没有读取到您的上传文件信息");
    }

    @GetMapping("/getTask")
    public Message getTask(String printStationCode){
        if(null==taskQueue.get(Integer.parseInt(printStationCode))){
            return Message.success(null);
        }
        TaskInfo poll = taskQueue.get(Integer.parseInt(printStationCode)).poll();
        return Message.success("1").add(poll);
    }

    @GetMapping("/getFill")
    public String getFill(HttpServletResponse response, String code) throws IOException {
        //待下载文件名

        File file = new File(fileMap.get(code));
        String fileName = fileMap.get(code).replace(";","");
        //设置为png格式的文件
        response.setHeader("content-type", "image/jpeg");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(file));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "employee/EmployeeDownloadFile";
    }

    private void addtoPrintQueue(int printStationCode,String rs,String name,String code){
        TaskInfo taskInfo=new TaskInfo(name,code,rs);
        if(null==taskQueue.get(printStationCode)){
            Queue<TaskInfo> queue=new LinkedList<>();
            this.taskQueue.put(printStationCode,queue);

        }
        this.taskQueue.get(printStationCode).offer(taskInfo);
        this.fileMap.put(code,rs);
    }

}
