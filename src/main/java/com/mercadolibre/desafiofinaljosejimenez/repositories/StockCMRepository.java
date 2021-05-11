package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.StockCM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockCMRepository extends JpaRepository<StockCM, Long> {
    Optional<StockCM> findByPart_id(Long partId);
}
