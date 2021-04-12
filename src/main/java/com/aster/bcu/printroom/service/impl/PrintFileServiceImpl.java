package com.aster.bcu.printroom.service.impl;

import com.aster.bcu.printroom.entity.Message;
import com.aster.bcu.printroom.service.PrintFileService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import java.awt.print.PrinterJob;
import java.io.*;

@Component
public class PrintFileServiceImpl implements PrintFileService {
    @Override
    public Message<String> printJPG(String path,File file,String printerName) {
        //File file=new File(path);
        if (file == null) return Message.fail("无效的文件");
        InputStream fileInput =null;
        DocFlavor flavor = null;
        try{
            flavor = DocFlavor.INPUT_STREAM.JPEG;
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new Copies(1)); //份数
            //aset.add(MediaSize.ISO.A4); //纸张
            // aset.add(Finishings.STAPLE);//装订
            aset.add(Sides.ONE_SIDED);//单双面
            PrintService printService = null;
            if (printerName != null) {
                //获得本台电脑连接的所有打印机
                PrintService[] printServices = PrinterJob.lookupPrintServices();
                if (printServices == null || printServices.length == 0) {
                    throw new Exception("打印失败，未找到可用打印机，请检查。");
                }
                for (int i = 0; i < printServices.length; i++) {
                    System.out.println(printServices[i].getName());
                    if (printServices[i].getName().contains(printerName)) {
                        printService = printServices[i];
                        break;
                    }
                }
                if (printService == null) {
                    throw new Exception("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
                }
            }
            fileInput = new FileInputStream(file); // 构造待打印的文件流
            Doc doc = new SimpleDoc(fileInput, flavor, null);
            DocPrintJob job = printService.createPrintJob(); // 创建打印作业
            job.print(doc, aset);

        }catch (Exception e){
                return Message.fail(e.toString());
        }
        return Message.success("打印成功");
    }

    @Override
    public Message<String> printDOC(File file,String printerName) {
        return null;
    }

    @Override
    public Message<String> printPDF(File file,String printerName) {
        return null;
    }

    @Override
    public Message<String> printTXT(File file,String printerName) {
        return null;
    }

    @Override
    public int pageCount(String fileType, MultipartFile multipartFile) throws IOException {

        byte [] byteArr=multipartFile.getBytes();
        InputStream file = new ByteArrayInputStream(byteArr);

        switch (fileType){
            case "image/jpeg":
            case "image/png":
                return 1;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document"://docx
                XWPFDocument wordDocx = new XWPFDocument (file);
                return wordDocx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
            case "application/msword"://doc
                HWPFDocument wordDoc = new HWPFDocument (file);
                return wordDoc.getSummaryInformation().getPageCount();
            case "application/pdf":
                PDDocument pdf = PDDocument.load(file);
                return pdf.getNumberOfPages();
            default: return -1;
        }
    }


}
