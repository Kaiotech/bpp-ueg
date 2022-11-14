package bppueg.repositories;

import bppueg.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findAllByUsername(String username);

    Optional<UserEntity> findAllByEmail(String email);

    Optional<UserEntity> findAllByUsernameAndEmail(String username, String email);

}
