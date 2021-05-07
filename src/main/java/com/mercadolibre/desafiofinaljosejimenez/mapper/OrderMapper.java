package com.mercadolibre.desafiofinaljosejimenez.mapper;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDetailDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDetailDE;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

   public static OrderResponseDTO mapOrderToResponse(OrderDE order){
      return null;
    }

    private static OrderDetailDTO mapOrderDetailsToResponse(OrderDetailDE order) {
       return new OrderDetailDTO(order.getPart().getPartCode(), order.getPart().getDescription(),order.getQuantity(),order.getAccountType().getDescription(), "no reason");

    }
}
