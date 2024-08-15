package com.dao;

import com.orm.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class UsuarioDAO extends PanacheEntity {

    public void detach (Usuario usuario) {
        getEntityManager().detach(usuario);
    }
}
