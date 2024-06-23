package com.starscream.soundgood.service;

import com.starscream.soundgood.dtos.reponse.PlayListRes;
import com.starscream.soundgood.dtos.request.PlayListReq;

import java.util.Set;

public interface PlaylistService {
    Set<PlayListRes> getAllPlaylistOfUser();

    PlayListRes createPlaylist(PlayListReq playlistReq);
}
