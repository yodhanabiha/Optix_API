package org.nabiha.mobileapi.features.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    Optional<ProductsEntity> findById(Long aLong);
}
