package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDE, Long> {

    //lista de pedidos de un concesionario con el estado de pedido de pedido seleccionado
    @Query("SELECT o FROM OrderDE o " +
            "JOIN DeliveryStatus ds on o.deliveryS.id = ds.id " +
            "JOIN Dealer d on o.dealer.id = d.id " +
            "WHERE ds.description = :deliveryStatus " +
            "AND d.dealerNumber = :dealerNumber " +
            "ORDER BY o.orderDate ASC")
    List<OrderDE> findByDealerAndStatusAscending(@Param("dealerNumber") String dealerNumber, @Param("deliveryStatus") String deliveryStatus);

    @Query("SELECT o FROM OrderDE o " +
            "JOIN DeliveryStatus ds on o.deliveryS.id = ds.id " +
            "JOIN Dealer d on o.dealer.id = d.id " +
            "WHERE ds.description = :deliveryStatus " +
            "AND d.dealerNumber = :dealerNumber " +
            "ORDER BY o.orderDate DESC")
    List<OrderDE> findByDealerAndStatusDescending(@Param("dealerNumber") String dealerNumber, @Param("deliveryStatus") String deliveryStatus);


    @Query("SELECT o FROM OrderDE o " +
            "JOIN Dealer d on o.dealer.id = d.id " +
            "WHERE d.dealerNumber = :dealerNumber " +
            "ORDER BY o.orderDate ASC")
    List<OrderDE> findByDealerAscending(@Param("dealerNumber") String dealerNumber);

    @Query("SELECT o FROM OrderDE o " +
            "JOIN Dealer d on o.dealer.id = d.id " +
            "WHERE d.dealerNumber = :dealerNumber " +
            "ORDER BY o.orderDate DESC")
    List<OrderDE> findByDealerDescending(@Param("dealerNumber") String dealerNumber);

    @Query("SELECT o FROM OrderDE o " +
            "JOIN Dealer d on o.dealer.id = d.id " +
            "WHERE o.dealer.dealerNumber = :dealerNumber")
    List<OrderDE> findByDealerRequest(@Param("dealerNumber") String dealerNumber);

    @Query("SELECT o FROM OrderDE o " +
            "JOIN DeliveryStatus ds on o.deliveryS.id = ds.id " +
            "JOIN Dealer d on o.dealer.id = d.id " +
            "WHERE ds.description = :deliveryStatus " +
            "AND d.dealerNumber = :dealerNumber " )
    List<OrderDE> findByDealerAndStatus(@Param("dealerNumber") String dealerNumber, @Param("deliveryStatus") String deliveryStatus);



}

