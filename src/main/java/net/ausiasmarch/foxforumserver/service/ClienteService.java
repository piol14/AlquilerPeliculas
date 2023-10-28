package net.ausiasmarch.foxforumserver.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity get(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Long create(ClienteEntity cliente) {
        clienteRepository.save(cliente);
        return cliente.getId();
    }

    public ClienteEntity update(ClienteEntity cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public Long delete(Long id) {
        clienteRepository.deleteById(id);
        return id;
    }

    public Page<ClienteEntity> getPage(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Long populate(Integer amount) {
        // Lógica para poblar la base de datos con datos de ejemplo
        return 0L;
    }
}
