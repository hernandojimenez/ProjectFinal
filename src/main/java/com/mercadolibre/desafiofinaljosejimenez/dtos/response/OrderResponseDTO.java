package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

import com.mercadolibre.desafiofinaljosejimenez.model.DeliveryStatus;

import java.util.Date;
import java.util.List;

public class OrderResponseDTO {

    private String orderNumber;
    private Date orderDate;
    private Date deliveryDate;
    private int daysDelay;
    private String deliveryStatus;
    List<OrderDetailDTO> orderDetails;

    public OrderResponseDTO(String orderNumber, Date orderDate, Date deliveryDate, int daysDelay, String deliveryStatus, List<OrderDetailDTO> orderDetails) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.daysDelay = daysDelay;
        this.deliveryStatus = deliveryStatus;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getDaysDelay() {
        return daysDelay;
    }

    public void setDaysDelay(int daysDelay) {
        this.daysDelay = daysDelay;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
