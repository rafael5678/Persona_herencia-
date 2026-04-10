package com.backend.Persona.service;

import com.backend.Persona.interfaces.Aprobador;
import com.backend.Persona.interfaces.Autenticable;
import com.backend.Persona.interfaces.Notificable;
import com.backend.Persona.model.Administrativo;
import com.backend.Persona.model.Notificacion;
import com.backend.Persona.model.Persona;
import com.backend.Persona.repository.AdministrativoRepository;
import com.backend.Persona.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrativoService implements Aprobador, Notificable, Autenticable {

    @Autowired
    private AdministrativoRepository repository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Administrativo> findAll() {
        return repository.findAll();
    }

    public Optional<Administrativo> findById(Long id) {
        return repository.findById(id);
    }

    public Administrativo create(Administrativo administrativo) {
        return repository.save(administrativo);
    }

    public Optional<Administrativo> update(Long id, Administrativo administrativo) {
        return repository.findById(id).map(a -> {
            administrativo.setId(id);
            return repository.save(administrativo);
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
    public void aprobarSolicitud(String codigoSolicitud) {
        System.out.println("Solicitud " + codigoSolicitud + " aprobada por administrativo");
    }

    @Override
    public void enviarNotificacion(Persona persona, String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje, persona);
        notificacionRepository.save(notificacion);
        System.out.println("Enviando y guardando notificación al administrativo " + persona.getNombre() + ": " + mensaje);
    }

    @Override
    public boolean login(String usuario, String password) {
        return "admin".equals(usuario) && "1234".equals(password);
    }
}
