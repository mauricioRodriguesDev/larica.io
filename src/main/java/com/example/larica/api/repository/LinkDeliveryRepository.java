package com.example.larica.api.repository;

import com.example.larica.api.domain.LinkDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDeliveryRepository extends JpaRepository<LinkDelivery, Long> { // Refatorado para Long
}
