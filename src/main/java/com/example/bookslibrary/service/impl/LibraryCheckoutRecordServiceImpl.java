package com.example.bookslibrary.service.impl;

import com.example.bookslibrary.db.model.LibraryCheckoutRecord;
import com.example.bookslibrary.db.repository.LibraryCheckoutRecordRepository;
import com.example.bookslibrary.service.interfaces.LibraryCheckoutRecordService;
import com.example.bookslibrary.web.rest.dto.SliceDto;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LibraryCheckoutRecordServiceImpl implements LibraryCheckoutRecordService {

    private final LibraryCheckoutRecordRepository libraryCheckoutRecordRepository;

    @Override
    public void createRecord(LibraryCheckoutRecord libraryCheckoutRecord) {
        libraryCheckoutRecord.setTakenDateTime(LocalDateTime.now());
        libraryCheckoutRecordRepository.save(libraryCheckoutRecord);
        log.info("Checkout record was created successfully");
    }

    @Transactional(readOnly = true)
    @Override
    public SliceDto<LibraryCheckoutRecordDto> getLibraryCheckoutRecords(int pageNumber, int pageSize) {
        log.info("Getting library checkout records page number {}, page size {}", pageNumber, pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("takenDateTime").descending());
        Slice<LibraryCheckoutRecordDto> slice = libraryCheckoutRecordRepository.findAllLibraryCheckoutRecords(pageRequest);
        return SliceDto.of(slice.getContent(), slice.hasNext(), slice.getNumber());
    }
}