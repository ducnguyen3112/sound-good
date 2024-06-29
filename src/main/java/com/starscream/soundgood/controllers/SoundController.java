package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.model.SoundFile;
import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.CreateSoundRes;
import com.starscream.soundgood.dtos.reponse.SoundPaginationRes;
import com.starscream.soundgood.dtos.reponse.SoundRes;
import com.starscream.soundgood.dtos.request.CreateSoundReq;
import com.starscream.soundgood.dtos.request.SoundsReq;
import com.starscream.soundgood.service.SoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("permitAll()")
    @PutMapping("/{id}/favorites")
    public ApiResponse<SoundRes> getSounds(@PathVariable Long id, @RequestParam Boolean isLiked) throws IOException {
        return ApiResponse.success(soundService.actionLiked(id, isLiked));
    }
}
