package com.backend.Persona.interfaces;

import com.backend.Persona.model.Persona;

public interface Notificable {
    void enviarNotificacion(Persona persona, String mensaje);
}
