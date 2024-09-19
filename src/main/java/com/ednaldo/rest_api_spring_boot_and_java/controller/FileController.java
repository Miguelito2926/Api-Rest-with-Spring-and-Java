package com.ednaldo.rest_api_spring_boot_and_java.controller;

import com.ednaldo.rest_api_spring_boot_and_java.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FileController {

    private final FileStorageService fileStorageService;

    // Endpoint para fazer o upload de um arquivo
    @PostMapping("/upload")
    @Operation(summary = "Faz o upload de um arquivo",
            description = "Recebe um arquivo multipart e o armazena no diretório de uploads.",
            tags = {"Arquivos"},
            responses = {
                    @ApiResponse(description = "Arquivo carregado com sucesso", responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(description = "Erro ao carregar o arquivo", responseCode = "500",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileStorageService.saveFile(file);
            return ResponseEntity.ok("Arquivo carregado com sucesso: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar o arquivo.");
        }
    }

    // Endpoint para fazer o download de um arquivo
    @GetMapping("/download/{filename}")
    @Operation(summary = "Faz o download de um arquivo",
            description = "Busca um arquivo armazenado pelo nome fornecido e o retorna como um recurso.",
            tags = {"Arquivos"},
            responses = {
                    @ApiResponse(description = "Arquivo encontrado e retornado com sucesso", responseCode = "200",
                            content = @Content(mediaType = "application/octet-stream")),
                    @ApiResponse(description = "Arquivo não encontrado", responseCode = "404",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(description = "Erro interno ao processar o download", responseCode = "500",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Optional<Resource> resourceOptional = fileStorageService.loadFile(filename);

        if (resourceOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = resourceOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Endpoint para deletar um arquivo
    @DeleteMapping("/delete/{filename}")
    @Operation(summary = "Deleta um arquivo",
            description = "Deleta um arquivo existente com base no nome fornecido.",
            tags = {"Arquivos"},
            responses = {
                    @ApiResponse(description = "Arquivo deletado com sucesso", responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(description = "Erro ao deletar o arquivo", responseCode = "500",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        try {
            fileStorageService.deleteFile(filename);
            return ResponseEntity.ok("Arquivo deletado com sucesso: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o arquivo.");
        }
    }

    // Endpoint para listar todos os arquivos no diretório de upload
    @GetMapping("/list")
    @Operation(summary = "Lista todos os arquivos",
            description = "Retorna uma lista com os nomes de todos os arquivos presentes no diretório de uploads.",
            tags = {"Arquivos"},
            responses = {
                    @ApiResponse(description = "Lista de arquivos retornada com sucesso", responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(description = "Erro ao listar os arquivos", responseCode = "500",
                            content = @Content(mediaType = "application/json"))
            })
    public ResponseEntity<String[]> listFiles() {
        try {
            String[] files = fileStorageService.listFiles();
            return ResponseEntity.ok(files);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
