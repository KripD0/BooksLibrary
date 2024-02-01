package com.example.bookslibrary.web.rest.interfaces;

import com.example.bookslibrary.web.dto.SliceDto;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import org.springframework.web.bind.annotation.RequestParam;

public interface LibraryCheckoutRecordController {

    SliceDto<LibraryCheckoutRecordDto> getLibraryCheckoutRecords(@RequestParam int pageNumber, @RequestParam int pageSize);
}