package com.backend.Persona.model;

import com.backend.Persona.interfaces.Aprobador;
import com.backend.Persona.interfaces.Autenticable;
import com.backend.Persona.interfaces.Notificable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "administrativos")
public class Administrativo extends Persona implements Autenticable, Notificable, Aprobador {
    @Column(nullable = false)
    private String area;

    public Administrativo(String nombre, String correo, String area) {
        super(nombre, correo);
        this.area = area;
    }

    public Administrativo(Long id, String nombre, String correo, String area) {
        super(id, nombre, correo);
        this.area = area;
    }

    @Override
    public boolean login(String usuario, String password) {
        System.out.println("Administrativo " + getNombre() + " intentando iniciar sesión con usuario: " + usuario);
        return true; // Simulación
    }

    @Override
    public void aprobarSolicitud(String codigoSolicitud) {
        System.out.println("Administrativo " + getNombre() + " aprobando la solicitud: " + codigoSolicitud);
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación al administrativo " + getNombre() + ": " + mensaje);
    }
}
