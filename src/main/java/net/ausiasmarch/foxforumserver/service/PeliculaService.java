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

    public PeliculaEntity get(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public Long create(PeliculaEntity pelicula) {
        peliculaRepository.save(pelicula);
        return pelicula.getId();
    }

    public PeliculaEntity update(PeliculaEntity pelicula) {
        peliculaRepository.save(pelicula);
        return pelicula;
    }

    public Long delete(Long id) {
        peliculaRepository.deleteById(id);
        return id;
    }

    public Page<PeliculaEntity> getPage(Pageable pageable) {
        return peliculaRepository.findAll(pageable);
    }

    public Long populate(Integer amount) {
        // Lógica para poblar la base de datos con datos de ejemplo
        return 0L;
    }
}
