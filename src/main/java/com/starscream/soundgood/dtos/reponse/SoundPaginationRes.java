package com.starscream.soundgood.dtos.reponse;

import com.starscream.soundgood.entities.Sound;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;


public class SoundPaginationRes extends PaginationRes<SoundRes> {
    public SoundPaginationRes(Page<Sound> page) {
        super(page);
        super.setData(page.getContent().stream().map(sound -> {
            SoundRes soundRes = new SoundRes();
            BeanUtils.copyProperties(sound, soundRes);
            return soundRes;
        }).toList());
    }

    public SoundPaginationRes(List<SoundRes> res, Page<Sound> page) {
        super(res, page);

    }
}
