package com.backend.Persona.controller;

import com.backend.Persona.dto.NotificarRequest;
import com.backend.Persona.model.Persona;
import com.backend.Persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @GetMapping
    public ResponseEntity<List<Persona>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/notificar")
    public ResponseEntity<Void> notificar(@PathVariable Long id, @RequestBody NotificarRequest request) {
        return service.findById(id)
                .map(p -> {
                    service.enviarNotificacion(id, request.getMensaje());
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
