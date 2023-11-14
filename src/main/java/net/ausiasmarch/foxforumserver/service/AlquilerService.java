package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.ausiasmarch.foxforumserver.entity.AlquilerEntity;
import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.entity.PeliculaEntity;
import net.ausiasmarch.foxforumserver.repository.AlquilerRepository;
import net.ausiasmarch.foxforumserver.repository.PeliculaRepository;
import net.ausiasmarch.foxforumserver.repository.ClienteRepository;

import java.sql.Date;
import java.util.Optional;



@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;
     @Autowired
private ClienteRepository clienteRepository;
 @Autowired
private PeliculaRepository peliculaRepository;
    public AlquilerEntity get(Long id) {
        return alquilerRepository.findById(id).orElse(null);
    }
    public Long create(AlquilerEntity alquiler) {
    alquilerRepository.save(alquiler);
        return alquiler.getId();
    }

 @Transactional
public AlquilerEntity update(AlquilerEntity updatedAlquiler) {
    Long id = updatedAlquiler.getId();
    AlquilerEntity existingAlquiler = alquilerRepository.findById(id).orElse(null);

    if (existingAlquiler == null) {
        throw new IllegalArgumentException("No se encontró un alquiler con ID " + id);
    }

    // Verifica que el cliente no sea nulo antes de asignarlo
    if (updatedAlquiler.getCliente() != null) {
        existingAlquiler.setCliente(updatedAlquiler.getCliente());
    } else {
        // Maneja el caso donde el cliente es nulo (puedes lanzar una excepción o tomar otra acción según tus necesidades)
        throw new IllegalArgumentException("El cliente no puede ser nulo");
    }

    // Actualiza otros campos
    existingAlquiler.setPelicula(updatedAlquiler.getPelicula());
    existingAlquiler.setFecha_alquiler(updatedAlquiler.getFecha_alquiler());
    existingAlquiler.setFecha_devolucion(updatedAlquiler.getFecha_devolucion());

    // Guarda los cambios en la base de datos
    try {
        alquilerRepository.save(existingAlquiler);
        return existingAlquiler;
    } catch (Exception e) {
        throw new RuntimeException("Error al actualizar el alquiler con ID " + id, e);
    }
}




    public Long delete(Long id) {
        alquilerRepository.deleteById(id);
        return id;
    }

    public Page<AlquilerEntity> getPage(Pageable pageable) {
        return alquilerRepository.findAll(pageable);
    }

    


    public Long populate(Integer amount) {
        for (int i = 0; i < amount; i++) {
            AlquilerEntity alquiler = new AlquilerEntity();
            
            // Se asume que ya existen registros de clientes y películas en la base de datos
            ClienteEntity cliente = clienteRepository.findById(3L).orElse(null); // Obtener el cliente con ID 3
            PeliculaEntity pelicula = peliculaRepository.findById(2L).orElse(null); // Obtener la película con ID 2

            if (cliente == null) {
                throw new IllegalArgumentException("No se encontró un cliente con ID 3");
            }

            if (pelicula == null) {
                throw new IllegalArgumentException("No se encontró una película con ID 2");
            }

            alquiler.setCliente(cliente);
            alquiler.setPelicula(pelicula);
            alquiler.setFecha_alquiler("12-2-2020");
            alquiler.setFecha_devolucion("12-2-2021");

            alquilerRepository.save(alquiler);
        }
        return amount.longValue();
}
}

