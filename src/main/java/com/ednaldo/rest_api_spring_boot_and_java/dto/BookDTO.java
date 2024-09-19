package com.ednaldo.rest_api_spring_boot_and_java.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookDTO extends RepresentationModel<BookDTO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty(value = "autor")
    private String author;

    @JsonProperty(value = "data_lancamento")
    private Date launch_date;

    @JsonProperty(value = "preco")
    private BigDecimal price;

    @JsonProperty(value = "titulo")
    private String title;

    @JsonProperty(value = "image")
    private String imageUrl;

    @JsonProperty("data_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("data_atualizacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}