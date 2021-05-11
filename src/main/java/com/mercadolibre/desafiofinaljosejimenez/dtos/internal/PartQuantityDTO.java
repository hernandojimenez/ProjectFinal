package com.mercadolibre.desafiofinaljosejimenez.dtos.internal;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;

@Validated
public class PartQuantityDTO {
    @Digits(integer=4,fraction = 0, message = "part code must have up to 4 numbers")
    private int partCode;
    @Digits(integer=4,fraction = 0, message = "part code must have up to 4 numbers")
    private int quantity;

    public int getPartCode() {
        return partCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPartCode(int partCode) {
        this.partCode = partCode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
