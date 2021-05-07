package com.mercadolibre.desafiofinaljosejimenez.mapper;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDetailDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDetailDE;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

   public static OrderResponseDTO mapOrderToResponse(OrderDE order){
      if (order == null ) return null;

       List<OrderDetailDTO> orderDetails = new ArrayList<>();
       for (OrderDetailDE orderDetail : order.getOrderDetailDE()) {
           OrderDetailDTO detailDTO = OrderMapper.mapOrderDetailsToResponse(orderDetail);
           orderDetails.add(detailDTO);
       }

        return new OrderResponseDTO(order.get):
    }

    private static OrderDetailDTO mapOrderDetailsToResponse(OrderDetailDE order) {
       return new OrderDetailDTO(order.getPart().getPartCode(), order.getPart().getDescription(),order.getQuantity(),order.getAccountType().getDescription(), "no reason");

    }
}
