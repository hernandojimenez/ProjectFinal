package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Validated
public class UpdateOrderDTO {

    @Pattern(regexp="^[0-9]{1,8}$",message="Order Number must have to 8 digits")
    private String orderNumber;

    @Pattern(regexp="^[C-F-D-P-c-f-d-p]{1}$",message="Order Status must be  C, P, D, F")
    private String orderStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
