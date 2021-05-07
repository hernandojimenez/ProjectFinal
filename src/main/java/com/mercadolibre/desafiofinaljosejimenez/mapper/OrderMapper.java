package com.mercadolibre.desafiofinaljosejimenez.mapper;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDetailDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDetailDE;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.PartRecord;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderMapper {

   public static OrderResponseDTO mapOrderToResponse(OrderDE order){
      if (order == null ) return null;

       List<OrderDetailDTO> orderDetails = new ArrayList<>();
       for (OrderDetailDE orderDetail : order.getOrderDetailDE()) {
           OrderDetailDTO detailDTO = OrderMapper.mapOrderDetailsToResponse(orderDetail,order.getOrderStatus().getDescription());
           orderDetails.add(detailDTO);
       }

       Date today = new Date();
       //SimpleDateFormat sdf  = new SimpleDateFormat( "yyyy-MM-dd");
       int daysDelayed = (int) ((( order.getDeliveryDate().getTime() - today.getTime())/ (1000 * 60 * 60 * 24)) % 365);
       if(daysDelayed < 0) daysDelayed = 0;
       return new OrderResponseDTO(order.getId(),order.getOrderDate(),order.getDeliveryDate(),daysDelayed,order.getDeliveryS().getDescription(),orderDetails);
    }

    private static OrderDetailDTO mapOrderDetailsToResponse(OrderDetailDE order,String reason) {
       return new OrderDetailDTO(order.getPart().getPartCode(), order.getPart().getDescription(),order.getQuantity(),order.getAccountType().getDescription(),reason);

    }
}
