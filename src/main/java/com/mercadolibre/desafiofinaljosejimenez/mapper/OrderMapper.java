package com.mercadolibre.desafiofinaljosejimenez.mapper;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.*;
import com.mercadolibre.desafiofinaljosejimenez.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
       return new OrderResponseDTO(order.getDealer().getSubsidiary().getSubsidiaryNumber()+"-"+order.getDealer().getDealerNumber()+"-"+order.getOrderNumber(),
               order.getOrderDate(),order.getDeliveryDate(),daysDelayed,order.getDeliveryS().getDescription(),orderDetails);
    }
    public static OrderResponseCMDTO mapOrderToResponseCM(OrderCM order,String dealerNumber){
        if (order == null ) return null;
        List<OrderDetailCMDTO> orderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetail()) {
            OrderDetailCMDTO detailDTO = OrderMapper.mapOrderDetailsCMToResponse(orderDetail,order.getOrderStatus().getDescription());
            orderDetails.add(detailDTO);
        }
        return new OrderResponseCMDTO(dealerNumber+"-"+order.getOrderNumber(),order.getOrderDate(),order.getOrderStatus().getDescription(),orderDetails);
    }

    private static OrderDetailDTO mapOrderDetailsToResponse(OrderDetailDE order,String reason) {
       return new OrderDetailDTO(order.getPart().getPartCode(), order.getPart().getDescription(),order.getQuantity(),order.getAccountType().getDescription(),reason);

    }
    private static OrderDetailCMDTO mapOrderDetailsCMToResponse(OrderDetail order,String reason) {
        return new OrderDetailCMDTO(order.getPart().getPartCode(), order.getPart().getDescription(),order.getQuantity(),order.getAccountType().getDescription(),reason,
                order.getPartStatus().getDescription());

    }
}
