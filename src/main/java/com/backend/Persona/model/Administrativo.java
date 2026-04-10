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
@Table(name = "administrativos")
public class Administrativo extends Persona {
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
}
