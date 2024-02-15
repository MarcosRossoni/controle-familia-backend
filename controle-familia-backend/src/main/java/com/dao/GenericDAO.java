package com.dao;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

public abstract class GenericDAO extends PanacheEntityBase {

    public Long nexValue(String seqName){
        return (long) getEntityManager()
                .createNativeQuery("SELECT nextval(:param)")
                .setParameter("param", seqName)
                .getSingleResult();
    }

}
