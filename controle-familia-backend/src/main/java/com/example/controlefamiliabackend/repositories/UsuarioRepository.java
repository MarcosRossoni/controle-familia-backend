package com.example.controlefamiliabackend.repositories;

import com.example.controlefamiliabackend.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, BigInteger> {
   boolean existsByDsEmail(String dsEmail);

   Optional<UsuarioModel> findByDsEmail(String dsEmail);



}
