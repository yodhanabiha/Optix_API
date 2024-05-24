package org.nabiha.mobileapi.features.likes;

import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<LikesEntity,Long> {
    List<LikesEntity> findAllByUser(UsersEntity user);

    List<LikesEntity> findAllByProduct(ProductsEntity products);
}
