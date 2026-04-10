package com.backend.Persona.controller;

import com.backend.Persona.dto.AprobarRequest;
import com.backend.Persona.dto.LoginRequest;
import com.backend.Persona.dto.NotificarRequest;
import com.backend.Persona.model.Administrativo;
import com.backend.Persona.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrativos")
public class AdministrativoController {

    @Autowired
    private AdministrativoService service;

    @GetMapping
    public ResponseEntity<List<Administrativo>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrativo> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Administrativo> create(@RequestBody Administrativo administrativo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(administrativo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrativo> update(@PathVariable Long id, @RequestBody Administrativo administrativo) {
        return service.update(id, administrativo)
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

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<Void> aprobar(@PathVariable Long id, @RequestBody AprobarRequest request) {
        return service.findById(id)
                .map(a -> {
                    service.aprobarSolicitud(request.getCodigoSolicitud());
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/notificar")
    public ResponseEntity<Void> notificar(@PathVariable Long id, @RequestBody NotificarRequest request) {
        return service.findById(id)
                .map(a -> {
                    service.enviarNotificacion(a, request.getMensaje());
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/login")
    public ResponseEntity<Boolean> login(@PathVariable Long id, @RequestBody LoginRequest request) {
        return service.findById(id)
                .map(a -> ResponseEntity.ok(service.login(request.getUsuario(), request.getPassword())))
                .orElse(ResponseEntity.notFound().build());
    }
}
