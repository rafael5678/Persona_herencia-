package com.backend.Persona.service;

import com.backend.Persona.interfaces.Autenticable;
import com.backend.Persona.interfaces.Evaluador;
import com.backend.Persona.interfaces.Notificable;
import com.backend.Persona.model.Estudiante;
import com.backend.Persona.model.Profesor;
import com.backend.Persona.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService implements Evaluador, Autenticable, Notificable {

    @Autowired
    private ProfesorRepository repository;

    public List<Profesor> findAll() {
        return repository.findAll();
    }

    public Optional<Profesor> findById(Long id) {
        return repository.findById(id);
    }

    public Profesor create(Profesor profesor) {
        return repository.save(profesor);
    }

    public Optional<Profesor> update(Long id, Profesor profesor) {
        return repository.findById(id).map(p -> {
            profesor.setId(id);
            return repository.save(profesor);
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
    public void evaluar(Estudiante estudiante, double nota) {
        System.out.println("Evaluando al estudiante " + estudiante.getNombre() + " con nota: " + nota);
    }

    @Override
    public boolean login(String usuario, String password) {
        return "admin".equals(usuario) && "1234".equals(password);
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación al profesor: " + mensaje);
    }
}
