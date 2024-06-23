package com.starscream.soundgood.dtos.reponse;

import com.starscream.soundgood.entities.Sound;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;


public class SoundPaginationRes extends PaginationRes<SoundRes> {
    public SoundPaginationRes(Page<Sound> page) {
        super(page);
        super.setData(page.getContent().stream().map(sound -> {
            SoundRes soundRes = new SoundRes();
            BeanUtils.copyProperties(sound, soundRes);
            return soundRes;
        }).toList());
    }
}
