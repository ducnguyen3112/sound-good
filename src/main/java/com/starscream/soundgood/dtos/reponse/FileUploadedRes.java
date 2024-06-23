package com.starscream.soundgood.dtos.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileUploadedRes {
    String filePath;

}
