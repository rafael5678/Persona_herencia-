package com.backend.Persona.model;

import com.backend.Persona.interfaces.Autenticable;
import com.backend.Persona.interfaces.Evaluador;
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
@Table(name = "profesores")
public class Profesor extends Persona implements Autenticable, Notificable, Evaluador {
    @Column(nullable = false)
    private String especialidad;

    public Profesor(String nombre, String correo, String especialidad) {
        super(nombre, correo);
        this.especialidad = especialidad;
    }

    public Profesor(Long id, String nombre, String correo, String especialidad) {
        super(id, nombre, correo);
        this.especialidad = especialidad;
    }

    @Override
    public boolean login(String usuario, String password) {
        System.out.println("Profesor " + getNombre() + " intentando iniciar sesión con usuario: " + usuario);
        return true; // Simulación
    }

    @Override
    public void evaluar(Estudiante estudiante, double nota) {
        System.out.println("Profesor " + getNombre() + " evaluando a " + estudiante.getNombre() + " con la nota: " + nota);
    }

    @Override
    public void enviarNotificacion(Persona persona, String mensaje) {
        System.out.println("Enviando notificación al profesor " + persona.getNombre() + ": " + mensaje);
    }
}
