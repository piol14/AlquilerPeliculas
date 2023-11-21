package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.PeliculaEntity;
import net.ausiasmarch.foxforumserver.repository.PeliculaRepository;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private SessionService oSessionService;
    public PeliculaEntity get(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public Long create(PeliculaEntity pelicula) {

        if ( oSessionService != null) {
            oSessionService.onlyAdmins();
        } else {
           
            throw new NullPointerException("SessionService is null");
        }
        peliculaRepository.save(pelicula);
        return pelicula.getId();
    }


    public PeliculaEntity update(PeliculaEntity pelicula) {
         oSessionService.onlyAdmins();
        peliculaRepository.save(pelicula);
        return pelicula;
    }

    public Long delete(Long id) {
        oSessionService.onlyAdmins();
        peliculaRepository.deleteById(id);
        return id;
    }

    public Page<PeliculaEntity> getPage(Pageable pageable) {

        return peliculaRepository.findAll(pageable);
    }

    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            PeliculaEntity pelicula = new PeliculaEntity();
            pelicula.setTitulo("Pelicula" + i);
            pelicula.setDirector("Director" + i);
            pelicula.setDuracion(120);  // Duración en minutos
            pelicula.setGenero("Género" + i);
          
            peliculaRepository.save(pelicula);
        }
        return amount.longValue();
    }
}
