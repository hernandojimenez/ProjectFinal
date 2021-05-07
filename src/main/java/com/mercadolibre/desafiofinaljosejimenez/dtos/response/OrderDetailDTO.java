package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

public class OrderDetailDTO {
    private int partCode;
    private String description;
    private int quantity;
    private String accountType;
    private String reason;

    public OrderDetailDTO(int partCode, String description, int quantity, String accountType, String reason) {
        this.partCode = partCode;
        this.description = description;
        this.quantity = quantity;
        this.accountType = accountType;
        this.reason = reason;
    }
}
