package com.dto.usuario;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record TrocarSenhaUsuarioDTO(String dsSenhaAntiga, String dsSenhaNova) {
}
