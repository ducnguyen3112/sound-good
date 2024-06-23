package com.starscream.soundgood.service.impl;

import com.starscream.soundgood.config.context.UserContext;
import com.starscream.soundgood.dtos.model.SoundFile;
import com.starscream.soundgood.dtos.reponse.CreateSoundRes;
import com.starscream.soundgood.dtos.reponse.SoundPaginationRes;
import com.starscream.soundgood.dtos.request.CreateSoundReq;
import com.starscream.soundgood.dtos.request.SoundsReq;
import com.starscream.soundgood.entities.AppUser;
import com.starscream.soundgood.entities.Sound;
import com.starscream.soundgood.exceptions.ValidationException;
import com.starscream.soundgood.repositories.SoundRepository;
import com.starscream.soundgood.service.SoundService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoundServiceImpl implements SoundService {

    private final SoundRepository soundRepository;
    private final UserContext userContext;

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
        Pageable pageRequest = PageRequest.of(req.getPage() - 1, req.getSize());
        Page<Sound> sounds;

        if (StringUtils.isBlank(req.getKeyword())) {
            sounds = soundRepository.findAll(pageRequest);
        } else {
            sounds = soundRepository.findAllByTitleContainingIgnoreCase(req.getKeyword(), pageRequest);
        }
        return new SoundPaginationRes(sounds);
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
        return SoundFile.builder().contentType(contentType).resource(resource).build();
    }
}
