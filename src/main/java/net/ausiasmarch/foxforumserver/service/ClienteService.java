package net.ausiasmarch.foxforumserver.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
   @Autowired
    HttpServletRequest oHttpServletRequest;
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
        for (int i = 0; i < amount; i++) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNombre("Cliente" + i);
            cliente.setDireccion("Dirección" + i);
            cliente.setTelefono("123456789" + i);
            cliente.setRol(false);
            cliente.setUsername("mitio"+i);
            cliente.setPassword( "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e" );
            clienteRepository.save(cliente);
        }
        return amount.longValue();
    }
}
