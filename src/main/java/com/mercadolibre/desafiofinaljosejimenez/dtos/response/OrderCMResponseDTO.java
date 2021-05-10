package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

import java.util.List;

public class OrderCMResponseDTO {
    private List<OrderResponseCMDTO> orders ;


    public OrderCMResponseDTO() {
    }

    public OrderCMResponseDTO(List<OrderResponseCMDTO> orders) {
        this.orders = orders;
    }

    public List<OrderResponseCMDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseCMDTO> orders) {
        this.orders = orders;
    }
}
