package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.StockCM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockCMRepository extends JpaRepository<StockCM, Long> {
    StockCM findByPart_id(Long partId);
}
