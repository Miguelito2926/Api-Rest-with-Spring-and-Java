package com.ednaldo.rest_api_spring_boot_and_java.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class FileStorageService {

    // Definindo o diretório padrão onde os arquivos serão salvos (neste caso, "uploads")
    private final Path uploadDir = Paths.get("uploads");

    // Construtor que inicializa o diretório de uploads
    public FileStorageService() throws IOException {
        // Cria o diretório de uploads se ele ainda não existir
        if (!Files.exists(uploadDir)) {
            System.out.println("Diretório de upload não encontrado. Criando diretório: " + uploadDir.toAbsolutePath());
            Files.createDirectories(uploadDir); // Cria o diretório
        } else {
            System.out.println("Diretório de upload já existente: " + uploadDir.toAbsolutePath());
        }
    }

    // Método para salvar um arquivo recebido
    public String saveFile(MultipartFile file) throws IOException {
        // Define o caminho do arquivo com base no nome original do arquivo
        Path filePath = uploadDir.resolve(file.getOriginalFilename());

        // Log do caminho onde o arquivo será salvo
        System.out.println("Salvando arquivo em: " + filePath.toAbsolutePath());

        // Copia o arquivo para o diretório de uploads, sobrescrevendo se já existir
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Log de confirmação de que o arquivo foi salvo
        System.out.println("Arquivo salvo com sucesso: " + file.getOriginalFilename());

        // Retorna o nome do arquivo salvo
        return file.getOriginalFilename();
    }

    // Método para carregar um arquivo com base no nome
    public Optional<Resource> loadFile(String filename) {
        try {
            // Resolve o caminho do arquivo com base no nome fornecido
            Path filePath = uploadDir.resolve(filename).normalize();

            // Cria um recurso que aponta para o arquivo
            Resource resource = new UrlResource(filePath.toUri());

            // Verifica se o arquivo existe e pode ser lido
            if (resource.exists() || resource.isReadable()) {
                // Log de sucesso ao carregar o arquivo
                System.out.println("Arquivo carregado com sucesso: " + filename);
                return Optional.of(resource);
            } else {
                // Log de falha ao carregar o arquivo
                System.out.println("Falha ao carregar o arquivo: " + filename + ". Arquivo não encontrado ou ilegível.");
                return Optional.empty();
            }
        } catch (MalformedURLException e) {
            // Log de erro se a URL do arquivo for inválida
            System.out.println("Erro ao carregar o arquivo: " + filename + ". URL malformada.");
            return Optional.empty();
        }
    }

    // Método para deletar um arquivo com base no nome
    public void deleteFile(String filename) throws IOException {
        // Resolve o caminho do arquivo a ser deletado
        Path filePath = uploadDir.resolve(filename).normalize();

        // Log do caminho do arquivo a ser deletado
        System.out.println("Tentando deletar o arquivo: " + filePath.toAbsolutePath());

        // Deleta o arquivo se ele existir
        if (Files.deleteIfExists(filePath)) {
            // Log de sucesso na exclusão
            System.out.println("Arquivo deletado com sucesso: " + filename);
        } else {
            // Log se o arquivo não foi encontrado para deletar
            System.out.println("Arquivo não encontrado para deletar: " + filename);
        }
    }

    // Método para listar os arquivos do diretório de uploads
    public String[] listFiles() throws IOException {
        return Files.list(uploadDir)
                .map(Path::getFileName)
                .map(Path::toString)
                .toArray(String[]::new);
    }
}
