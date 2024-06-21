package org.nabiha.mobileapi.features.histories;

import org.nabiha.mobileapi.features.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriesRepository extends JpaRepository<HistoriesEntity,Long> {
    List<HistoriesEntity> findAllByUser(UsersEntity user);
}
