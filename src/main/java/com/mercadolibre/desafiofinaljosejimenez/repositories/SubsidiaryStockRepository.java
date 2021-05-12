package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.StockCM;
import com.mercadolibre.desafiofinaljosejimenez.model.SubsidiaryStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubsidiaryStockRepository extends JpaRepository<SubsidiaryStock, Long> {
    Optional<SubsidiaryStock> findByPart_id(Long partId);
}
