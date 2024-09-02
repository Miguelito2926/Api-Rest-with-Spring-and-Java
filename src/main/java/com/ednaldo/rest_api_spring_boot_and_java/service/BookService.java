package com.ednaldo.rest_api_spring_boot_and_java.service;

import com.ednaldo.rest_api_spring_boot_and_java.dto.BookDTO;
import com.ednaldo.rest_api_spring_boot_and_java.entities.Book;
import com.ednaldo.rest_api_spring_boot_and_java.exceptions.ResourceNotFoundException;
import com.ednaldo.rest_api_spring_boot_and_java.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));
        return modelMapper.map(book, BookDTO.class);
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @PutMapping(value = "/{id}")
    public BookDTO update(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));
        updateData(book, bookDTO);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para este ID: " + id));
        bookRepository.delete(book);
    }

    private void updateData(Book book, BookDTO bookDTO) {
        book.setAuthor(bookDTO.getAuthor());
        book.setLaunch_date(bookDTO.getLaunch_date());
        book.setPrice(bookDTO.getPrice());
        book.setTitle(bookDTO.getTitle());
    }
}
