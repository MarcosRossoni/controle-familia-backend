package com.controller;

import com.enumeration.LogEnum;
import com.orm.Logs;
import com.orm.Usuario;

import java.time.LocalDateTime;

public abstract class GenericController {

    protected void registrarLog(Usuario usuario, String dsDescricao, LogEnum fgTipoLog){

        Logs logs = new Logs(
                LocalDateTime.now(),
                dsDescricao,
                fgTipoLog,
                usuario
        );

        logs.persist();
    }
}
