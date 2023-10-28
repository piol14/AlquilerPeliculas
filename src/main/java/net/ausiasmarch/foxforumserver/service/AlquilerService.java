package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.AlquilerEntity;
import net.ausiasmarch.foxforumserver.repository.AlquilerRepository;

@Service
public class AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;

    public AlquilerEntity get(Long id) {
        return alquilerRepository.findById(id).orElse(null);
    }

    public Long create(AlquilerEntity alquiler) {
        alquilerRepository.save(alquiler);
        return alquiler.getId();
    }

    public AlquilerEntity update(AlquilerEntity alquiler) {
        alquilerRepository.save(alquiler);
        return alquiler;
    }

    public Long delete(Long id) {
        alquilerRepository.deleteById(id);
        return id;
    }

    public Page<AlquilerEntity> getPage(Pageable pageable) {
        return alquilerRepository.findAll(pageable);
    }

    public Long populate(Integer amount) {
        // Lógica para poblar la base de datos con datos de ejemplo
        return 0L;
    }
}
