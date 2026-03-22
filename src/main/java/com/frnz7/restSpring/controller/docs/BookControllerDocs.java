package com.frnz7.restSpring.controller.docs;

import com.frnz7.restSpring.data.dto.BookDTO;
import com.frnz7.restSpring.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookControllerDocs {
    @Operation(
            summary = "Finds all Books",
            description = "Finds all Books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = PersonDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    List<BookDTO> findAll();

    @Operation(
            summary = "Find Books by id",
            description = "Finds Books by id",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = PersonDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    BookDTO findById(@PathVariable Long id);


    @Operation(
            summary = "Create new book",
            description = "create new books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "201",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = PersonDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    BookDTO create(@RequestBody BookDTO bookDTO);


    @Operation(
            summary = "update book",
            description = "update new books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "201",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(
                                            schema = @Schema(implementation = PersonDTO.class)))
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    BookDTO update(@RequestBody BookDTO bookDTO);


    @Operation(summary = "Delete a specific person by id", description = "Delete person by id", tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    void delete(@PathVariable Long id);
}
