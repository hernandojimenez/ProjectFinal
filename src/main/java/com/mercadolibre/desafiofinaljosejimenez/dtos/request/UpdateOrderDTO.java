package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validated
public class UpdateOrderDTO {
    @NotNull(message = "Order Number must not be null")
    @Pattern(regexp="^[0-9]{1,8}$",message="Order Number must have to 8 numbers")
    private String orderNumber;

    @NotNull(message = "Order Status must not be null")
    @Pattern(regexp="^[C-F-D-P]{1}$",message="Order Status must be  C, P, D, F")
    private String orderStatus;

    public UpdateOrderDTO(@NotNull(message = "Order Number must not be null") @Pattern(regexp = "^[0-9]{1,8}$", message = "Order Number must have to 8 numbers") String orderNumber, @NotNull(message = "Order Status must not be null") @Pattern(regexp = "^[C-F-D-P]{1}$", message = "Order Status must be  C, P, D, F") String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
    }

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
