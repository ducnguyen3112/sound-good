package com.starscream.soundgood.dtos.reponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreateSoundRes {
    Long id;
    String title;
    String soundPath;
    String coverPath;
}
