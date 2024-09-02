package com.ednaldo.rest_api_spring_boot_and_java.controller;

import com.ednaldo.rest_api_spring_boot_and_java.dto.BookDTO;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.InvalidFormatEmailException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.service.BookService;
import com.ednaldo.rest_api_spring_boot_and_java.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import java.util.List;



@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/books")
@Tag(name = "Livros", description = "Endpoints para Gerenciamento de Books")
public class BookController {

    private final BookService bookService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Retorna uma lista de Livros",
            tags = {"Livros"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
                    }),

            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

            @ApiResponse(description = "Not Found", responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
    })
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> list = bookService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Cria um novo Livro",
            tags = {"Livros"}, responses = {
            @ApiResponse(description = "Create", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())
    })

    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        BookDTO bookCreated = bookService.create(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCreated);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Retorna um Livro específica pelo ID",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
            })
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);
        return ResponseEntity.ok().body(bookDTO);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Atualiza um Livro existente",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Update", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
            })

    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO BookDTO = bookService.update(bookDTO, id);
        return ResponseEntity.ok().body(BookDTO);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Remove um Livro por ID",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
