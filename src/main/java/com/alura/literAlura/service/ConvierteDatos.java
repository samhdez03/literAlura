package com.alura.literAlura.service;

import com.alura.literAlura.model.DatosLibro;
import com.alura.literAlura.model.Respuesta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            Respuesta respuesta = objectMapper.readValue(json, Respuesta.class);
            if (respuesta.getResults() != null && respuesta != null && !respuesta.getResults().isEmpty()) {
                DatosLibro datosLibro = respuesta.getResults().get(0);
                System.out.println("Primer libro " + datosLibro);
                return (T) datosLibro;
            } else {
                throw new RuntimeException("No se encontraron libros.");
            } }
        catch(JsonProcessingException e){
                throw new RuntimeException("Error al deserializar JSON: " + e.getMessage(), e);
            }

        }
    }
