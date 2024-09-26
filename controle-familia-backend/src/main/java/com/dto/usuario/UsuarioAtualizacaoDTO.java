package com.dto.usuario;

import com.dto.cidade.CidadeDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record UsuarioAtualizacaoDTO(
        Long idUsuario,
        String dsNome,
        String dsTelefone,
        String dtNascimento,
        String dsCpf,
        String dsEndereco,
        Long numPredial,
        String dsBairro,
        String dsComplemento,
        CidadeDTO cidade) {
}
