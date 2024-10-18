package com.upiiz.biblioteca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upiiz.biblioteca.models.Biblioteca;
import com.upiiz.biblioteca.services.BibliotecaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bibliotecas")
public class BibliotecaController {
    //Requiero inyectar el servicio
    BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService){
        this.bibliotecaService = bibliotecaService;
    }

    //Get de todas
    @GetMapping
    public ResponseEntity<List<Biblioteca>> getAllBibliotecas(){
        return ResponseEntity.ok(bibliotecaService.getAllBibliotecas());
    }

    //Get de una
    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> getBiblioteca(@PathVariable Long id){
        return ResponseEntity.ok(bibliotecaService.getBibliotecaById(id));
    }

    //Post - crear una cat
    @PostMapping
    public ResponseEntity<Biblioteca> addBiblioteca(@RequestBody Biblioteca biblioteca){
        return ResponseEntity.ok(bibliotecaService.createBiblioteca(biblioteca));
    }

    //PUT - actualizar una cat
    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> updateBiblioteca(@RequestBody Biblioteca biblioteca, @PathVariable Long id){
        biblioteca.setId(id);
        return ResponseEntity.ok(bibliotecaService.updateBiblioteca(biblioteca));
    }

    //Delete - elimibnar una cat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBiblioteca(@PathVariable Long id){
        bibliotecaService.deleteBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}
