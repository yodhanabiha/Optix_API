package org.nabiha.mobileapi.features.carts;

import org.nabiha.mobileapi.features.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository<CartsEntity, Long> {
    List<CartsEntity> findAllByUser(UsersEntity users);

}
