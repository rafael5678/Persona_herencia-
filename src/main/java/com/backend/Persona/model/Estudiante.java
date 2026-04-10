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
@Table(name = "estudiantes")
public class Estudiante extends Persona {
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
}
