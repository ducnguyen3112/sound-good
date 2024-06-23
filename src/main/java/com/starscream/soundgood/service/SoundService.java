package com.starscream.soundgood.service;

import com.starscream.soundgood.dtos.model.SoundFile;
import com.starscream.soundgood.dtos.reponse.CreateSoundRes;
import com.starscream.soundgood.dtos.reponse.SoundPaginationRes;
import com.starscream.soundgood.dtos.request.CreateSoundReq;
import com.starscream.soundgood.dtos.request.SoundsReq;

import java.io.IOException;

public interface SoundService {
    CreateSoundRes createSound(CreateSoundReq createSoundReq);

    SoundPaginationRes getAllSound(SoundsReq req);

    SoundFile getSoundFile(Long id) throws IOException;
}
