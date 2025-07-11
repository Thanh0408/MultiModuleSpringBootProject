package demo.com.vn.repository;

import demo.com.vn.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

    Optional<BuildingEntity> findByAddress(String address);
    Page<BuildingEntity> findAll(Pageable pageable);
}

