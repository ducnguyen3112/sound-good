package com.starscream.soundgood.dtos.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SoundFile {
    Resource resource;
    String contentType;
    HttpHeaders headers;
}
