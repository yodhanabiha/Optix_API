package org.nabiha.mobileapi.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByEmail(String email);

}
