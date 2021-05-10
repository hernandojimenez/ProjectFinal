package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

import java.util.Date;
import java.util.List;

public class OrderResponseCMDTO {
    private String orderNumber;
    private Date orderDate;
    private String orderStatus;
    List<OrderDetailCMDTO> orderDetails;

    public OrderResponseCMDTO(String orderNumber, Date orderDate, String orderStatus, List<OrderDetailCMDTO> orderDetails) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetailCMDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailCMDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
