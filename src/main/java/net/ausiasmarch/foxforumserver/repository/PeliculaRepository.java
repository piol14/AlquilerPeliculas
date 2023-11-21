package net.ausiasmarch.foxforumserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.foxforumserver.entity.PeliculaEntity;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long>{
      @Modifying
    @Query(value = "ALTER TABLE pelicula AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}

