package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.model.SoundFile;
import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.CreateSoundRes;
import com.starscream.soundgood.dtos.reponse.SoundPaginationRes;
import com.starscream.soundgood.dtos.request.CreateSoundReq;
import com.starscream.soundgood.dtos.request.SoundsReq;
import com.starscream.soundgood.service.SoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sounds")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoundController {

    private final SoundService soundService;

    @PostMapping
    public ApiResponse<CreateSoundRes> createSound(@RequestBody CreateSoundReq request) {
        return ApiResponse.success(soundService.createSound(request));
    }

    @GetMapping
    public ApiResponse<SoundPaginationRes> getSounds(SoundsReq req) {
        return ApiResponse.success(soundService.getAllSound(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {
        SoundFile soundFile = soundService.getSoundFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(soundFile.getContentType()))
                .headers(soundFile.getHeaders())
                .body(soundFile.getResource());
    }
}
