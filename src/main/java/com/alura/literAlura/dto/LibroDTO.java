package com.alura.literAlura.dto;

import com.alura.literAlura.model.DatosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
        String titulo,
        List<String> idiomas,
        Integer numeroDescargas,
        List<DatosAutor> autor
) {

}
