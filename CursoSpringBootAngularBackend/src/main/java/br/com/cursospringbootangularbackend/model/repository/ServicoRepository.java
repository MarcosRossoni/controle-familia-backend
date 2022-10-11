package br.com.cursospringbootangularbackend.model.repository;

import br.com.cursospringbootangularbackend.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
