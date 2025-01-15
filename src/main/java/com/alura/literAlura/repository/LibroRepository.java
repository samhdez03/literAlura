package com.alura.literAlura.repository;

import com.alura.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    @Query(value = "SELECT * FROM libros l WHERE :idioma = ANY(l.idiomas)", nativeQuery = true)
    List<Libro> buscarPorIdioma(@Param("idioma") String idioma);
}
