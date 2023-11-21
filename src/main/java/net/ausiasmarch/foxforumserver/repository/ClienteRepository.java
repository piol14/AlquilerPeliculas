package net.ausiasmarch.foxforumserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.foxforumserver.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{
      Optional<ClienteEntity> findByUsername(String username);

    Optional<ClienteEntity> findByUsernameAndPassword(String username, String password);
  @Modifying
    @Query(value = "ALTER TABLE cliente AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}

