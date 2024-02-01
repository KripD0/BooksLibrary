package com.example.bookslibrary.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class SliceDto<T> {

    private boolean hasNext;
    private int pageNumber;
    private List<T> items;

    public static <T> SliceDto<T> of(List<T> content, boolean hasNext, int pageNumber) {
        SliceDto<T> result = new SliceDto<>();
        result.setItems(content);
        result.setHasNext(hasNext);
        result.setPageNumber(pageNumber);
        return result;
    }
}