package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.OrderCM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCMRepository extends JpaRepository<OrderCM,Long> {

    @Query("SELECT p FROM Part " +
            "WHERE p. = :deliveryStatus " +
            "AND d.dealerNumber = :dealerNumber " )
    List<OrderCM> findBy(@Param("dealerNumber") String dealerNumber, @Param("deliveryStatus") String deliveryStatus);


}
