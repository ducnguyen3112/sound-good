package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.dtos.reponse.FileUploadedRes;
import com.starscream.soundgood.exceptions.UnexpectedException;
import com.starscream.soundgood.exceptions.ValidationException;
import com.starscream.soundgood.service.FileService;
import com.starscream.soundgood.utils.FileUploadUtil;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.file.upload.dir}")
    private String uploadDir = "uploads/";

    @Override
    public FileUploadedRes uploadFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (file.isEmpty()) {
            throw new ValidationException(Collections.singletonList("File is empty."));
        }

        try {
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            byte[] bytes = file.getBytes();
            String ogFileName = file.getOriginalFilename();
            String fileExtension;
            String fileName;
            if (StringUtils.isNotEmpty(ogFileName) && ogFileName.contains(".")) {
                fileName = ogFileName.substring(0, ogFileName.lastIndexOf("."));
                fileExtension = FileUploadUtil.getFileExtension(ogFileName);
            } else {
                throw new ValidationException(Collections.singletonList("File name is invalid."));
            }

            Date date = new Date();
            fileName = fileName + "-" + date.getTime() + "." + fileExtension;
            Path path = Paths.get(uploadDir + fileName);
            Files.write(path, bytes);
            return FileUploadedRes.builder().filePath(path.toString()).build();
        } catch (IOException e) {
            throw new UnexpectedException("Error while uploading file.");
        }
    }
}
