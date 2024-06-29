package com.starscream.soundgood.dtos.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationReq {
    int page;
    int size;
    String keyword;
}
