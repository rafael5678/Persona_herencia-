package com.backend.Persona.util;

import com.backend.Persona.model.Administrativo;
import com.backend.Persona.model.Estudiante;
import com.backend.Persona.model.Profesor;
import com.backend.Persona.repository.AdministrativoRepository;
import com.backend.Persona.repository.EstudianteRepository;
import com.backend.Persona.repository.ProfesorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;
    private final AdministrativoRepository administrativoRepository;

    public DataLoader(EstudianteRepository estudianteRepository,
                      ProfesorRepository profesorRepository,
                      AdministrativoRepository administrativoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
        this.administrativoRepository = administrativoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (estudianteRepository.count() == 0 && profesorRepository.count() == 0 && administrativoRepository.count() == 0) {
            System.out.println("Cargando datos iniciales...");

            // Crear Estudiantes
            estudianteRepository.save(new Estudiante("Juan Perez", "juan@ejemplo.com", "EST001"));
            estudianteRepository.save(new Estudiante("Maria Lopez", "maria@ejemplo.com", "EST002"));

            // Crear Profesores
            profesorRepository.save(new Profesor("Carlos Gomez", "carlos@ejemplo.com", "Matemáticas"));
            profesorRepository.save(new Profesor("Ana Martinez", "ana@ejemplo.com", "Física"));

            // Crear Administrativos
            administrativoRepository.save(new Administrativo("Pedro Ruiz", "pedro@ejemplo.com", "Recursos Humanos"));
            administrativoRepository.save(new Administrativo("Lucia Diaz", "lucia@ejemplo.com", "Contabilidad"));

            System.out.println("Datos iniciales cargados con éxito.");
        } else {
            System.out.println("La base de datos ya contiene datos, saltando carga inicial.");
        }
    }
}
