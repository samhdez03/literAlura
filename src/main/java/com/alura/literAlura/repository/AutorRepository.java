package com.alura.literAlura.repository;

import com.alura.literAlura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autores, Long> {
    Optional<Autores> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autores a WHERE a.nacimiento <= :anio AND a.fallecimiento >= :anio")
    List<Autores> autoresPorAnio(int anio);
}
