package br.com.cursospringbootangularbackend.model.repository;

import br.com.cursospringbootangularbackend.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
