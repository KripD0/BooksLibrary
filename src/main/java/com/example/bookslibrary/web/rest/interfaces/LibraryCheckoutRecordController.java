package com.example.bookslibrary.web.rest.interfaces;

import com.example.bookslibrary.web.rest.dto.LibraryCheckoutRecordDto;
import com.example.bookslibrary.web.rest.dto.SliceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Library checkout record")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/plain"))
public interface LibraryCheckoutRecordController {

    @Operation(summary = "Get library checkout records sorted and paged or empty content")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SliceDto.class))
    )
    SliceDto<LibraryCheckoutRecordDto> getLibraryCheckoutRecords(
            @Parameter(description = "Page number", required = true)
            @RequestParam int pageNumber,
            @Parameter(description = "Page size", required = true)
            @RequestParam int pageSize);
}