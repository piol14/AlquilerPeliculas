package net.ausiasmarch.foxforumserver.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Pattern;
import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.repository.ClienteRepository;

@Service


public class ClienteService {
     private final String flowflewPASSWORD = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";

    @Autowired
    private ClienteRepository clienteRepository;
   @Autowired
    HttpServletRequest oHttpServletRequest;
    @Autowired
     private SessionService oSessionService;
    public ClienteEntity get(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Long create(ClienteEntity cliente) {
               
oSessionService.onlyAdmins();
        clienteRepository.save(cliente);
        return cliente.getId();
    }
  public ClienteEntity getByUsername(String username) {
        return clienteRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }


    public ClienteEntity update(ClienteEntity cliente) {
         ClienteEntity oUserEntityFromDatabase = this.get(cliente.getId());
        oSessionService.onlyAdminsOrUsersWithIisOwnData(oUserEntityFromDatabase.getId());
        if (oSessionService.isUser()) {
            cliente.setId(null);
            cliente.setRol(oUserEntityFromDatabase.getRol());
            cliente.setPassword(flowflewPASSWORD );
            return clienteRepository.save(cliente);
        } else {
            cliente.setId(null);
            cliente.setPassword(flowflewPASSWORD );
            return clienteRepository.save(cliente);
        }
    }
    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        clienteRepository.deleteById(id);
        return id;
    }

    public Page<ClienteEntity> getPage(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
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
    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        clienteRepository.deleteAll();
        clienteRepository.resetAutoIncrement();
        clienteRepository.flush();
        return clienteRepository.count();
    }
}
