package com.upiiz.biblioteca.services;

import org.springframework.stereotype.Service;
import com.upiiz.biblioteca.models.Biblioteca;
import com.upiiz.biblioteca.repository.BibliotecaRepository;

import java.util.List;

@Service
public class BibliotecaService {
    //rEQUERIMOS EL REPO DE (DATOS-LISTADO) DE CATEGORIAS)
    BibliotecaRepository bibliotecaRepository;

    //Constructor -cuando creo ella instancia le pasa el repositorio
    public BibliotecaService(BibliotecaRepository bibliotecaRepository){
        this.bibliotecaRepository = bibliotecaRepository;
    }

    //Get promotions
    public List<Biblioteca> getAllBibliotecas(){
        return bibliotecaRepository.obtenerBibliotecas();
    }

    public Biblioteca getBibliotecaById(Long id){
        return bibliotecaRepository.obtenerBibliotecaById(id);
    }

    public Biblioteca createBiblioteca(Biblioteca biblioteca){
        return bibliotecaRepository.guardarBiblioteca(biblioteca);
    }

    public Biblioteca updateBiblioteca(Biblioteca biblioteca){
        return bibliotecaRepository.actualizarBiblioteca(biblioteca);
    }

    public void deleteBiblioteca(Long id){
        bibliotecaRepository.eliminarBiblioteca(id);
    }
}
