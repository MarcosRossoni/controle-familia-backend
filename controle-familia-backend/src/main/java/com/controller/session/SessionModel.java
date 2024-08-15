package com.controller.session;

import com.orm.Usuario;

public class SessionModel {

    private final Usuario usuario;

    public SessionModel(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
