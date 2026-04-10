package com.backend.Persona.service;

import com.backend.Persona.model.Notificacion;
import com.backend.Persona.model.Persona;
import com.backend.Persona.repository.NotificacionRepository;
import com.backend.Persona.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Persona> findAll() {
        return repository.findAll();
    }

    public Optional<Persona> findById(Long id) {
        return repository.findById(id);
    }

    public void enviarNotificacion(Long personaId, String mensaje) {
        repository.findById(personaId).ifPresent(persona -> {
            Notificacion notificacion = new Notificacion(mensaje, persona);
            notificacionRepository.save(notificacion);
            System.out.println("Notificación genérica guardada para " + persona.getNombre() + " (ID: " + personaId + ")");
        });
    }
}
