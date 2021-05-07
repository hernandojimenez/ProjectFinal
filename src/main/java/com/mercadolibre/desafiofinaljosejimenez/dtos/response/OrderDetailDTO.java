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

    public int getPartCode() {
        return partCode;
    }

    public void setPartCode(int partCode) {
        this.partCode = partCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
