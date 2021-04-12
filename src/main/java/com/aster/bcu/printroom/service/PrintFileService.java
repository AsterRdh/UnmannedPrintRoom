package com.aster.bcu.printroom.service;

import com.aster.bcu.printroom.entity.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface PrintFileService {
    public Message<String> printJPG(String path,File file,String printerName);
    public Message<String> printDOC(File file,String printerName);
    public Message<String> printPDF(File file,String printerName);
    public Message<String> printTXT(File file,String printerName);

    public int pageCount(String fileType, MultipartFile multipartFile) throws IOException;

}
