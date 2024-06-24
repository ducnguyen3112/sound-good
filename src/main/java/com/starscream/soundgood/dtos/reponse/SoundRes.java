package com.starscream.soundgood.dtos.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoundRes {
    Long id;
    String title;
    String soundPath;
    String coverPath;
    boolean liked;
}
