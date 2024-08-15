package com.dao;

import com.orm.Auth;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class AuthDAO extends PanacheEntity {

    public void detach (Auth auth) {
        getEntityManager().detach(auth);
    }
}
