package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.config.context.UserContext;
import com.starscream.soundgood.dtos.model.SoundFile;
import com.starscream.soundgood.dtos.reponse.CreateSoundRes;
import com.starscream.soundgood.dtos.reponse.SoundPaginationRes;
import com.starscream.soundgood.dtos.reponse.SoundRes;
import com.starscream.soundgood.dtos.request.CreateSoundReq;
import com.starscream.soundgood.dtos.request.SoundsReq;
import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.entities.Sound;
import com.starscream.soundgood.entities.UserSound;
import com.starscream.soundgood.entities.UserSoundId;
import com.starscream.soundgood.exceptions.ValidationException;
import com.starscream.soundgood.repositories.SoundRepository;
import com.starscream.soundgood.repositories.UserSoundRepository;
import com.starscream.soundgood.service.SoundService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoundServiceImpl implements SoundService {

    private final SoundRepository soundRepository;
    private final UserContext userContext;
    private final UserSoundRepository userSoundRepository;

    @Override
    public CreateSoundRes createSound(CreateSoundReq createSoundReq) {
        AppUser appUser = userContext.getUser();
        Sound sound = new Sound();
        sound.setUploadedBy(appUser);
        BeanUtils.copyProperties(createSoundReq, sound);
        sound = soundRepository.save(sound);
        CreateSoundRes res = CreateSoundRes.builder().build();
        BeanUtils.copyProperties(sound, res);
        return res;
    }

    @Override
    public SoundPaginationRes getAllSound(SoundsReq req) {
        AppUser appUser = userContext.getUser();
        Pageable pageRequest = PageRequest.of(req.getPage() - 1, req.getSize());
        Page<Sound> sounds;

        sounds = soundRepository.findSongsByCriteria(req.getKeyword(),
                (req.getIsLiked() != null && req.getIsLiked()) ? appUser.getId() : null, req.getPlayListId()
                , pageRequest);
        List<SoundRes> res = sounds.getContent().stream().map(sound -> {
            SoundRes soundRes = new SoundRes();
            BeanUtils.copyProperties(sound, soundRes);
            if (appUser != null) {
                Optional<UserSound> userSound = userSoundRepository.findById_UserIdAndId_SoundId(appUser.getId(), sound.getId());
                soundRes.setLiked(userSound.isPresent());
            }
            return soundRes;
        }).toList();

        return new SoundPaginationRes(res, sounds);
    }

    @Override
    public SoundFile getSoundFile(Long id) throws IOException {
        Sound sound = soundRepository.findById(id).orElseThrow(() -> new ValidationException("Sound not found!"));
        Path filePath = Paths.get(sound.getSoundPath()).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("File not found");
        }
        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentLength(resource.contentLength());
        return SoundFile.builder().contentType(contentType).resource(resource).headers(headers).build();
    }

    @Transactional
    @Override
    public SoundRes actionLiked(Long id, boolean isLike) {
        Sound sound = soundRepository.findById(id).orElseThrow(() -> new ValidationException("Sound not found!"));
        AppUser appUser = userContext.getUser();

        UserSoundId userSoundId = new UserSoundId(appUser.getId(), sound.getId());
        UserSound userSound = userSoundRepository.findById(userSoundId).orElse(null);

        if (isLike) {
            if (userSound == null) {
                userSound = new UserSound();
                userSound.setId(userSoundId);
                userSound.setUser(appUser);
                userSound.setSound(sound);
                userSoundRepository.save(userSound);
            }
        } else {
            if (userSound != null) {
                userSoundRepository.delete(userSound);
            }
        }
        SoundRes res = new SoundRes();
        BeanUtils.copyProperties(sound, res);
        res.setLiked(isLike);
        return res;
    }
}
