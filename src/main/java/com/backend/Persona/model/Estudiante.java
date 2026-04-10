package com.backend.Persona.model;

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
@Table(name = "estudiantes")
public class Estudiante extends Persona implements Autenticable, Notificable {
    @Column(nullable = false, unique = true)
    private String codigo;

    public Estudiante(String nombre, String correo, String codigo) {
        super(nombre, correo);
        this.codigo = codigo;
    }

    public Estudiante(Long id, String nombre, String correo, String codigo) {
        super(id, nombre, correo);
        this.codigo = codigo;
    }

    @Override
    public boolean login(String usuario, String password) {
        System.out.println("Estudiante " + getNombre() + " intentando iniciar sesión con usuario: " + usuario);
        return true; // Simulación
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación al estudiante " + getNombre() + ": " + mensaje);
    }
}
