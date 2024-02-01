package com.example.bookslibrary.service.interfaces;

import com.example.bookslibrary.db.model.LibraryCheckoutRecord;
import com.example.bookslibrary.web.rest.dto.SliceDto;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;

public interface LibraryCheckoutRecordService {

    void createRecord(LibraryCheckoutRecord libraryCheckoutRecord);

    SliceDto<LibraryCheckoutRecordDto> getLibraryCheckoutRecords(int pageNumber, int pageSize);
}