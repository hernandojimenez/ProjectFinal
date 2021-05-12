package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Validated
public class UpdateDeliveryDTO {

    @Pattern(regexp="^[0-9]{1,8}$",message="Order Number must have to 8 numbers")
    private String orderNumber;

    @Pattern(regexp="^[C-F-D-P]{1}$",message="Order Status must be  C, P, D, F")
    private String deliveryStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String orderStatus) {
        this.deliveryStatus = orderStatus;
    }
}