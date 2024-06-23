package com.starscream.soundgood.dtos.reponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationRes<T> {
    List<T> data;
    int page;
    int size;
    int totalElement;
    int totalPages;

    public <E> PaginationRes(Page<E> page) {
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElement = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
    }
}
