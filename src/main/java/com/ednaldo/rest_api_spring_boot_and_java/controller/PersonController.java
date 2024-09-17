package com.ednaldo.rest_api_spring_boot_and_java.controller;


import com.ednaldo.rest_api_spring_boot_and_java.dto.PersonDTO;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.InvalidFormatEmailException;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.service.PersonService;
import com.ednaldo.rest_api_spring_boot_and_java.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/persons")
@Tag(name = "Pessoas", description = "Endpoints para Gerenciamento de Pessoas")
public class PersonController {

    private final PersonService personService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Retorna uma lista paginada de pessoas",
            description = "Busca uma lista de pessoas com suporte à paginação. Permite ajustar o número da página e a quantidade de itens por página.",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Lista de pessoas retornada com sucesso", responseCode = "200",
                            content = {
                                    @Content(mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))
                            }),
                    @ApiResponse(description = "Requisição inválida devido a parâmetros incorretos", responseCode = "400",
                            content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))
                            }),
                    @ApiResponse(description = "Nenhuma pessoa encontrada", responseCode = "404",
                            content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))
                            }),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<Page<PersonDTO>> pageListPersons(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, sortDirection);
        Page<PersonDTO> personDTOPage = personService.findAll(pageable);
        return ResponseEntity.ok(personDTOPage);
    }


    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Cria um novo usuário",
            tags = {"Pessoas"}, responses = {
            @ApiResponse(description = "Usuário criado com sucesso", responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "Erro de validação do email", responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),
            @ApiResponse(description = "Recurso não encontrado", responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),
            @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content())
    })
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        // Validate the personDTO here if necessary
        PersonDTO personCreated = personService.create(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Retorna uma pessoa específica pelo ID",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
            })
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        PersonDTO personDTO = personService.findById(id);
        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Atualiza uma pessoa existente",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Update", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
            })
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        PersonDTO PersonDTO = personService.update(personDTO, id);
        return ResponseEntity.ok().body(PersonDTO);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Remove uma pessoa por ID",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),

                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),

                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content()),
            })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/disable/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Desativa uma pessoa específica pelo ID", tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InvalidFormatEmailException.class))}),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceNotFoundException.class))}),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content())
            })
    public ResponseEntity<PersonDTO> disablePerson(@PathVariable Long id) {
        PersonDTO personDTO = personService.disable(id);
        return ResponseEntity.ok().body(personDTO);
    }
}


