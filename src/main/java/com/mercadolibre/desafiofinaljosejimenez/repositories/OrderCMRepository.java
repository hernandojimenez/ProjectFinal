package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.OrderCM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCMRepository extends JpaRepository<OrderCM,Long> {


    //lista de pedidos de un concesionario con el estado de pedido de pedido seleccionado
    @Query("SELECT ocm FROM OrderCM ocm " +
            "JOIN Subsidiary s on ocm.subsidiary.id=s.id " +
            "JOIN Dealer d on s.id= d.subsidiary.id " +
            "JOIN OrderDE ode on d.id=ode.dealer.id " +
            "WHERE s.subsidiaryNumber = :subsidiaryNumber " +
            "AND d.dealerNumber = :dealerNumber " +
            "AND ocm.orderNumber = :orderNumber " +
            "ORDER BY ode.orderDate ASC")
    List<OrderCM> findByDealerAndStatusAscending(@Param("subsidiaryNumber") String subsidiaryNumber, @Param("dealerNumber") String dealerNumber, @Param("orderNumber") String orderNumber);

    Optional<OrderCM> findOrderCMSByOrderNumber(@Param("orderNumber") String orderNumber);
}
