package com.backend.Persona.controller;

import com.backend.Persona.dto.EvaluarRequest;
import com.backend.Persona.dto.LoginRequest;
import com.backend.Persona.dto.NotificarRequest;
import com.backend.Persona.model.Profesor;
import com.backend.Persona.service.EstudianteService;
import com.backend.Persona.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService service;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Profesor>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profesor> create(@RequestBody Profesor profesor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(profesor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> update(@PathVariable Long id, @RequestBody Profesor profesor) {
        return service.update(id, profesor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/evaluar")
    public ResponseEntity<Void> evaluar(@PathVariable Long id, @RequestBody EvaluarRequest request) {
        return service.findById(id).map(p -> {
            return estudianteService.findById(request.getEstudianteId()).map(e -> {
                service.evaluar(e, request.getNota());
                return ResponseEntity.ok().<Void>build();
            }).orElse(ResponseEntity.notFound().build());
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/login")
    public ResponseEntity<Boolean> login(@PathVariable Long id, @RequestBody LoginRequest request) {
        return service.findById(id)
                .map(p -> ResponseEntity.ok(service.login(request.getUsuario(), request.getPassword())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/notificar")
    public ResponseEntity<Void> notificar(@PathVariable Long id, @RequestBody NotificarRequest request) {
        return service.findById(id)
                .map(p -> {
                    service.enviarNotificacion(p, request.getMensaje());
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
