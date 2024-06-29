package com.starscream.soundgood.service;

import com.starscream.soundgood.dtos.reponse.FileUploadedRes;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadedRes uploadFile(MultipartFile file);
}
