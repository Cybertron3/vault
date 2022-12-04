package com.tijori.vault.Repository;

import com.tijori.vault.TijoriEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TijoriRepository extends JpaRepository<TijoriEntity, UUID> {
    Optional<TijoriEntity> findByUserId(String userId);

    Boolean existsByUserId(String userId);


    @Query(value = "select key from tijori where user_id = ?1", nativeQuery = true)
    String getHashedkeyForUserId( String userId);
}
