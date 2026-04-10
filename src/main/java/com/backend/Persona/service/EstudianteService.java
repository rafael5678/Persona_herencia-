package com.backend.Persona.service;

import com.backend.Persona.interfaces.Autenticable;
import com.backend.Persona.interfaces.Notificable;
import com.backend.Persona.model.Estudiante;
import com.backend.Persona.model.Notificacion;
import com.backend.Persona.model.Persona;
import com.backend.Persona.repository.EstudianteRepository;
import com.backend.Persona.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements Autenticable, Notificable {

    @Autowired
    private EstudianteRepository repository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Estudiante> findAll() {
        return repository.findAll();
    }

    public Optional<Estudiante> findById(Long id) {
        return repository.findById(id);
    }

    public Estudiante create(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Optional<Estudiante> update(Long id, Estudiante estudiante) {
        return repository.findById(id).map(e -> {
            estudiante.setId(id);
            return repository.save(estudiante);
        });
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(String usuario, String password) {
        return "admin".equals(usuario) && "1234".equals(password);
    }

    @Override
    public void enviarNotificacion(Persona persona, String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje, persona);
        notificacionRepository.save(notificacion);
        System.out.println("Enviando y guardando notificación al estudiante " + persona.getNombre() + ": " + mensaje);
    }
}
