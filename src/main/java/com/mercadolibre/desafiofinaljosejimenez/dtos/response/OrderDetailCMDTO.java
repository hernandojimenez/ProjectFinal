package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

public class OrderDetailCMDTO {
    private int partCode;
    private String description;
    private int quantity;
    private String accountType;
    private String reason;
    private String partStatus;

    public OrderDetailCMDTO(int partCode, String description, int quantity, String accountType, String reason, String partStatus) {
        this.partCode = partCode;
        this.description = description;
        this.quantity = quantity;
        this.accountType = accountType;
        this.reason = reason;
        this.partStatus = partStatus;
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

    public String getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(String partStatus) {
        this.partStatus = partStatus;
    }
}
