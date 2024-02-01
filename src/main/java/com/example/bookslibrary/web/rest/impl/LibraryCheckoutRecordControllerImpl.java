package com.example.bookslibrary.web.rest.impl;

import com.example.bookslibrary.service.interfaces.LibraryCheckoutRecordService;
import com.example.bookslibrary.web.rest.dto.SliceDto;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import com.example.bookslibrary.web.rest.interfaces.LibraryCheckoutRecordController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${bookslibrary.controller.basePath}/checkout")
@RequiredArgsConstructor
public class LibraryCheckoutRecordControllerImpl implements LibraryCheckoutRecordController {

    private final LibraryCheckoutRecordService libraryCheckoutRecordService;

    @GetMapping
    @Override
    public SliceDto<LibraryCheckoutRecordDto> getLibraryCheckoutRecords(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return libraryCheckoutRecordService.getLibraryCheckoutRecords(pageNumber, pageSize);
    }
}