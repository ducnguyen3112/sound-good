package com.starscream.soundgood.controllers;

import com.starscream.soundgood.dtos.reponse.ApiResponse;
import com.starscream.soundgood.dtos.reponse.PlayListRes;
import com.starscream.soundgood.dtos.request.PlayListReq;
import com.starscream.soundgood.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ApiResponse<Set<PlayListRes>> getPlaylists() {
        return ApiResponse.success(playlistService.getAllPlaylistOfUser());
    }

    @PostMapping
    public ApiResponse<PlayListRes> createPlaylist(@RequestBody final PlayListReq playlistReq) {
        return ApiResponse.success(playlistService.createPlaylist(playlistReq));
    }
}
