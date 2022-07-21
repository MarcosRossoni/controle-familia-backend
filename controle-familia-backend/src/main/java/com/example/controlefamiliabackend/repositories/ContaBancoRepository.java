package com.example.controlefamiliabackend.repositories;

import com.example.controlefamiliabackend.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ContaBancoRepository extends JpaRepository<UsuarioModel, BigInteger> {
}
