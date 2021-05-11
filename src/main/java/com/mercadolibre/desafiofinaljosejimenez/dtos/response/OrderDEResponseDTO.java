package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class OrderDEResponseDTO {
    private String dealerNumber;
    private List<OrderResponseDTO> orders ;

    public OrderDEResponseDTO() {
    }

    public OrderDEResponseDTO(String dealerNumber, List<OrderResponseDTO> orders) {
        this.dealerNumber = dealerNumber;
        this.orders = orders;
    }

    public OrderDEResponseDTO(List<OrderResponseDTO> orders) {
        this.orders = orders;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public List<OrderResponseDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseDTO> orders) {
        this.orders = orders;
    }
}
