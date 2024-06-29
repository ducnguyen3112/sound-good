package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.FileUploadedRes;
import com.starscream.soundgood.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ApiResponse<FileUploadedRes> uploadFile(@RequestParam("file") MultipartFile file) {
        return ApiResponse.success(fileService.uploadFile(file));
    }
}
