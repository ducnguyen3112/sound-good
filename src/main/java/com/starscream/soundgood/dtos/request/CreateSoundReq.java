package com.starscream.soundgood.dtos.request;

import lombok.Data;

@Data
public class CreateSoundReq {
    String title;
    String soundPath;
    String coverPath;
}
