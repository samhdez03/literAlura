package com.alura.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDTO(
        String nombre,
        Integer nacimiento,
        Integer fallecimiento
) {
}
