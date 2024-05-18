package com.example.warehouse.Repository;

import com.example.warehouse.models.DealModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<DealModel,Long> {
}
