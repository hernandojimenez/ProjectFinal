package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

import java.util.Date;
import java.util.List;

public class OrderResponseDTO {

    private String orderNumber;
    private Date orderDate;
    private Date deliveryDate;
    private int daysDelay;
    private String deliveryStatus;
    List<OrderDetailDTO> orderDetails;

}
