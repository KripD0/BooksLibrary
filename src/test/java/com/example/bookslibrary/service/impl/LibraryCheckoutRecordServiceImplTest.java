package com.example.bookslibrary.service.impl;

import com.example.bookslibrary.db.model.LibraryCheckoutRecord;
import com.example.bookslibrary.db.repository.LibraryCheckoutRecordRepository;
import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import com.example.bookslibrary.web.rest.dto.SliceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.SliceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryCheckoutRecordServiceImplTest {

    @Mock
    private LibraryCheckoutRecordRepository libraryCheckoutRecordRepository;
    @InjectMocks
    private LibraryCheckoutRecordServiceImpl libraryCheckoutRecordService;
    @Captor
    private ArgumentCaptor<LibraryCheckoutRecord> libraryCheckoutRecordArgumentCaptor;

    @Test
    @DisplayName("createRecord should be completed")
    void createRecordShouldBeCompleted() {
        LibraryCheckoutRecord libraryCheckoutRecord = new LibraryCheckoutRecord();

        libraryCheckoutRecordService.createRecord(libraryCheckoutRecord);

        verify(libraryCheckoutRecordRepository).save(libraryCheckoutRecordArgumentCaptor.capture());
        LibraryCheckoutRecord capturedValue = libraryCheckoutRecordArgumentCaptor.getValue();
        assertNotNull(capturedValue.getTakenDateTime());
    }

    @Test
    @DisplayName("getLibraryCheckoutRecords should be completed")
    void getLibraryCheckoutRecordsShouldBeCompleted() {
        List<LibraryCheckoutRecordDto> libraryCheckoutRecordDtos = Arrays.asList(new LibraryCheckoutRecordDto(), new LibraryCheckoutRecordDto());

        when(libraryCheckoutRecordRepository.findAllLibraryCheckoutRecords(any())).thenReturn(new SliceImpl<>(libraryCheckoutRecordDtos));

        SliceDto<LibraryCheckoutRecordDto> actualValues = libraryCheckoutRecordService.getLibraryCheckoutRecords(0, 10);

        assertEquals(libraryCheckoutRecordDtos.size(), actualValues.getItems().size());
    }
}