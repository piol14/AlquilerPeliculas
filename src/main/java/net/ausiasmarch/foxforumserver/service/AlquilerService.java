package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.ausiasmarch.foxforumserver.entity.AlquilerEntity;
import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.entity.DuracionAlquiler;
import net.ausiasmarch.foxforumserver.entity.PeliculaEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.repository.AlquilerRepository;
import net.ausiasmarch.foxforumserver.repository.PeliculaRepository;
import net.ausiasmarch.foxforumserver.repository.ClienteRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;



@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;
     @Autowired
private ClienteRepository clienteRepository;
 @Autowired
private PeliculaRepository peliculaRepository;
 @Autowired
private SessionService sessionService;
    public AlquilerEntity get(Long id) {
        return alquilerRepository.findById(id).orElse(null);
    }
    public Long create(AlquilerEntity alquiler) {
        sessionService.onlyAdminsOrUsers();
    
        if (alquiler.getPrecio() != 0.0) {
            // Calcula el precio final según la duración si es necesario
            if (alquiler.getDuracion() != null) {
                alquiler.setFecha_alquiler(LocalDate.now());
                switch (alquiler.getDuracion()) {
                    case SEMANA:
                        alquiler.setPrecio(alquiler.getPrecio() * 1.2);
                        alquiler.setFecha_devolucion(alquiler.getFecha_alquiler().plusWeeks(1));
                        alquiler.setDuracion(DuracionAlquiler.SEMANA);
                        break;
                    case MES:
                        alquiler.setPrecio(alquiler.getPrecio() * 1.5);
                        alquiler.setFecha_devolucion(alquiler.getFecha_alquiler().plusMonths(1));
                        alquiler.setDuracion(DuracionAlquiler.MES);
                        break;
                    case ANIO:
                        alquiler.setPrecio(alquiler.getPrecio() * 2.0);
                        alquiler.setFecha_devolucion(alquiler.getFecha_alquiler().plusYears(1));
                          alquiler.setDuracion(DuracionAlquiler.ANIO);
                        break;
                    default:
                        throw new IllegalArgumentException("Duración de alquiler no válida");
                }
            } else {
                // Si la duración es nula, establece la fecha de devolución igual a la fecha de alquiler
                alquiler.setFecha_devolucion(alquiler.getFecha_alquiler());
            }
        }
    
        if (sessionService.isUser()) {
            alquiler.setCliente(sessionService.getSessionUser());
        }
    
        return alquilerRepository.save(alquiler).getId();
    }
    
    




@Transactional
public AlquilerEntity update(AlquilerEntity updatedAlquiler) {
    AlquilerEntity oAlquilerEntityFromDatabase = this.get(updatedAlquiler.getId());
    sessionService.onlyAdminsOrUsersWithIisOwnData(oAlquilerEntityFromDatabase.getCliente().getId());

    if (oAlquilerEntityFromDatabase == null) {
        throw new IllegalArgumentException("No se encontró un alquiler con ID " + updatedAlquiler.getId());
    }

    // Verifica que el cliente no sea nulo antes de asignarlo
    if (updatedAlquiler.getCliente() != null) {
        oAlquilerEntityFromDatabase.setCliente(updatedAlquiler.getCliente());
    } else {
        // Maneja el caso donde el cliente es nulo (puedes lanzar una excepción o tomar otra acción según tus necesidades)
        throw new IllegalArgumentException("El cliente no puede ser nulo");
    }

    // Actualiza otros campos
      
            oAlquilerEntityFromDatabase.setPelicula(updatedAlquiler.getPelicula());
            oAlquilerEntityFromDatabase.setFecha_alquiler(LocalDate.now());

            // Actualiza siempre el precio basado en la nueva duración
            switch (updatedAlquiler.getDuracion()) {

                case SEMANA:
                    oAlquilerEntityFromDatabase.setPrecio(updatedAlquiler.getPrecio() * 1.2);
                    oAlquilerEntityFromDatabase.setFecha_devolucion((oAlquilerEntityFromDatabase.getFecha_alquiler().plusWeeks(1)));
                    oAlquilerEntityFromDatabase.setDuracion(DuracionAlquiler.SEMANA);
                    break;
                case MES:
                oAlquilerEntityFromDatabase.setDuracion(DuracionAlquiler.MES);
                    oAlquilerEntityFromDatabase.setPrecio(updatedAlquiler.getPrecio() * 1.5);
                    oAlquilerEntityFromDatabase.setFecha_devolucion(oAlquilerEntityFromDatabase.getFecha_alquiler().plusMonths(1));
                    
                    break;
                case ANIO:
                    oAlquilerEntityFromDatabase.setPrecio(updatedAlquiler.getPrecio() * 2.0);
                    oAlquilerEntityFromDatabase.setFecha_devolucion((oAlquilerEntityFromDatabase.getFecha_alquiler().plusYears(1)));
                    oAlquilerEntityFromDatabase.setDuracion(DuracionAlquiler.ANIO);
                    break;
                default:
                    throw new IllegalArgumentException("Duración de alquiler no válida");
            }

            // Guarda los cambios en la base de datos
            alquilerRepository.save(oAlquilerEntityFromDatabase);
            return oAlquilerEntityFromDatabase;
        
   
}






    public Long delete(Long id) {
      AlquilerEntity oAlquilerEntityFromDatabase = this.get(id);
        sessionService.onlyAdminsOrUsersWithIisOwnData(oAlquilerEntityFromDatabase.getCliente().getId());
        alquilerRepository.deleteById(id);
        return id;
    }

    public Page<AlquilerEntity> getPage(Pageable pageable) {
        return alquilerRepository.findAll(pageable);
    }

    


    public Long populate(Integer amount) {
        sessionService.onlyAdmins();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = 0; i < amount; i++) {
            AlquilerEntity alquiler = new AlquilerEntity();

            // Se asume que ya existen registros de clientes y películas en la base de datos
            ClienteEntity cliente = clienteRepository.findById(3L).orElse(null); // Obtener el cliente con ID 3
            PeliculaEntity pelicula = peliculaRepository.findById(3L).orElse(null); // Obtener la película con ID 2

            if (cliente == null) {
                throw new IllegalArgumentException("No se encontró un cliente con ID 3");
            }

            if (pelicula == null) {
                throw new IllegalArgumentException("No se encontró una película con ID 2");
            }

            alquiler.setCliente(cliente);
            alquiler.setPelicula(pelicula);
             alquiler.setFecha_alquiler(LocalDate.now());
             alquiler.setFecha_devolucion(alquiler.getFecha_alquiler().plusWeeks(1));
            alquiler.setDuracion(DuracionAlquiler.SEMANA);
           alquiler.setPrecio(1.99  );
            alquilerRepository.save(alquiler);
        }
        return amount.longValue();
    
}
 @Transactional
    public Long empty() {
        sessionService.onlyAdmins();
        alquilerRepository.deleteAll();
        alquilerRepository.resetAutoIncrement();
       alquilerRepository.flush();
        return alquilerRepository.count();
    }
}
