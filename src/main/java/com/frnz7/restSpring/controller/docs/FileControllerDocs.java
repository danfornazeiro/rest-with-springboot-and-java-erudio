package com.frnz7.restSpring.controller.docs;

import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.data.dto.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File EndPoint", description = "Endpoints for file upload/multipleUpload and download")
public interface FileControllerDocs {

    @Operation(summary = "Upload file", description = "Upload file", tags = {"File"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "201",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = UploadFileResponseDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    UploadFileResponseDTO uploadFile(MultipartFile file);

    @Operation(summary = "Upload multiple files", description = "Upload multiple files", tags = {"File"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "201",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = UploadFileResponseDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] files);

    @Operation(summary = "Download file", description = "Download file", tags = {"File"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = UploadFileResponseDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request);

}
