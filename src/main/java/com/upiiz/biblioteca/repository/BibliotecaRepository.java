package com.upiiz.biblioteca.repository;

import org.springframework.stereotype.Repository;
import com.upiiz.biblioteca.models.Biblioteca;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BibliotecaRepository {

    private List<Biblioteca> bibliotecas;
    private AtomicLong id;
    private ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper para manejar JSON
    private final String jsonFilePath = "json/libros.json"; // Ruta del archivo JSON

    public BibliotecaRepository() {
        // Cargar el archivo JSON al inicializar el repositorio
        cargarBibliotecasDesdeArchivo();
        this.id = new AtomicLong(bibliotecas.size() > 0 ? bibliotecas.get(bibliotecas.size() - 1).getId() : 0);
    }

    // Cargar las bibliotecas desde el archivo JSON
    private void cargarBibliotecasDesdeArchivo() {
        try {
            File file = new File(jsonFilePath);
            if (file.exists()) {
                bibliotecas = objectMapper.readValue(file, new TypeReference<List<Biblioteca>>() {});
            } else {
                bibliotecas = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            bibliotecas = new ArrayList<>();
        }
    }

    // Guardar las bibliotecas en el archivo JSON
    private void guardarBibliotecasEnArchivo() {
        try {
            objectMapper.writeValue(new File(jsonFilePath), bibliotecas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GET todas las bibliotecas
    public List<Biblioteca> obtenerBibliotecas() {
        return bibliotecas;
    }

    // GET solo una biblioteca por id
    public Biblioteca obtenerBibliotecaById(Long id) {
        return bibliotecas.stream().filter(biblioteca -> biblioteca.getId().equals(id)).findFirst().orElse(null);
    }

    // POST: Guardar una nueva biblioteca
    public Biblioteca guardarBiblioteca(Biblioteca biblioteca) {
        biblioteca.setId(id.incrementAndGet());
        bibliotecas.add(biblioteca);
        guardarBibliotecasEnArchivo(); // Guardar los cambios en el archivo
        return biblioteca;
    }

    // DELETE: Eliminar una biblioteca por id
    public void eliminarBiblioteca(Long id) {
        bibliotecas.removeIf(biblioteca -> biblioteca.getId().equals(id));
        guardarBibliotecasEnArchivo(); // Guardar los cambios en el archivo
    }

    // PUT: Actualizar una biblioteca existente
    public Biblioteca actualizarBiblioteca(Biblioteca biblioteca) {
        eliminarBiblioteca(biblioteca.getId());
        bibliotecas.add(biblioteca);
        guardarBibliotecasEnArchivo(); // Guardar los cambios en el archivo
        return biblioteca;
    }
}


