package bppueg.repositories;

import bppueg.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Page<UserEntity> findAllByUsername(String username, Pageable pageable);

    Page<UserEntity> findAllByEmail(String email, Pageable pageable);

    Page<UserEntity> findAllByUsernameAndEmail(String username, String email, Pageable pageable);

}
