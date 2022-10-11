package br.com.cursospringbootangularbackend.rest;

import br.com.cursospringbootangularbackend.model.entity.Cliente;
import br.com.cursospringbootangularbackend.model.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlller {

    private final ClienteRepository repository;

    public ClienteControlller(ClienteRepository repository){
        this.repository = repository;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }
}
