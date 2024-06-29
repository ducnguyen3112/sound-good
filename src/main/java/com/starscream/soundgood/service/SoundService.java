package com.starscream.soundgood.service;

import com.starscream.soundgood.dtos.model.SoundFile;
import com.starscream.soundgood.dtos.reponse.CreateSoundRes;
import com.starscream.soundgood.dtos.reponse.SoundPaginationRes;
import com.starscream.soundgood.dtos.reponse.SoundRes;
import com.starscream.soundgood.dtos.request.CreateSoundReq;
import com.starscream.soundgood.dtos.request.SoundsReq;
import jakarta.transaction.Transactional;

import java.io.IOException;

public interface SoundService {
    CreateSoundRes createSound(CreateSoundReq createSoundReq);

    SoundPaginationRes getAllSound(SoundsReq req);

    SoundFile getSoundFile(Long id) throws IOException;

    @Transactional
    SoundRes actionLiked(Long id, boolean isLike) throws IOException;
}
