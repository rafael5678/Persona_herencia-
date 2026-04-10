package com.backend.Persona.controller;

import com.backend.Persona.dto.LoginRequest;
import com.backend.Persona.dto.NotificarRequest;
import com.backend.Persona.model.Estudiante;
import com.backend.Persona.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(estudiante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        return service.update(id, estudiante)
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

    @PostMapping("/{id}/login")
    public ResponseEntity<Boolean> login(@PathVariable Long id, @RequestBody LoginRequest request) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok(service.login(request.getUsuario(), request.getPassword())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/notificar")
    public ResponseEntity<Void> notificar(@PathVariable Long id, @RequestBody NotificarRequest request) {
        return service.findById(id)
                .map(e -> {
                    service.enviarNotificacion(e, request.getMensaje());
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
