package com.controller.converter;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

abstract class GenericConverter<ORM, DTO> {

    public abstract DTO ormToDto(ORM orm);

    public abstract ORM dtoToOrm(DTO dto);

    public abstract DTO ormToDto(ORM orm, DTO dto);

    public abstract ORM dtoToOrm(DTO dto, ORM orm);

    protected abstract String[] ignoreProperties();

    public void copy(Object from, Object to) {
        BeanUtils.copyProperties(from, to, ignoreProperties());
    }

    public List<DTO> ormToDto(List<ORM> listORM) {

        if (listORM == null){
            return null;
        }

        List<DTO> listDTO = new ArrayList<>();
        for (ORM orm : listORM) {
            listDTO.add(ormToDto(orm));
        }

        return listDTO;
    }

    public List<ORM> dtoToOrm(List<DTO> listDTO) {

        if (listDTO == null){
            return null;
        }

        List<ORM> listORM = new ArrayList<>();
        for (DTO dto : listDTO) {
            listORM.add(dtoToOrm(dto));
        }

        return listORM;
    }

}
