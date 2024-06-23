package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.config.context.UserContext;
import com.starscream.soundgood.dtos.reponse.PlayListRes;
import com.starscream.soundgood.dtos.request.PlayListReq;
import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.entities.Playlist;
import com.starscream.soundgood.repositories.PlaylistRepository;
import com.starscream.soundgood.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserContext userContext;

    @Override
    public Set<PlayListRes> getAllPlaylistOfUser() {
        AppUser currentUser = userContext.getUser();
        Set<Playlist> playlists = playlistRepository.findByCreatedBy_IdOrderByCreatedDateDesc(currentUser.getId());
        return playlists
                .stream()
                .map(playlist -> PlayListRes.builder()
                        .id(playlist.getId())
                        .title(playlist.getTitle())
                        .build())
                .collect(Collectors.toSet());
    };

    @Override
    public PlayListRes createPlaylist(PlayListReq playlistReq) {
        AppUser currentUser = userContext.getUser();
        Playlist playlist = new Playlist();
        BeanUtils.copyProperties(playlistReq, playlist);
        playlist.setCreatedBy(currentUser);
        playlist = playlistRepository.save(playlist);
        PlayListRes res = new PlayListRes();
        BeanUtils.copyProperties(playlist, res);
        return res;
    }


}
