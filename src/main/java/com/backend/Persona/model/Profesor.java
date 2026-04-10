package com.backend.Persona.model;

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
public class Profesor extends Persona {
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
}
