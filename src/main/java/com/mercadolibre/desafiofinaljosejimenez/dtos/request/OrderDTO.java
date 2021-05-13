package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import com.mercadolibre.desafiofinaljosejimenez.dtos.internal.PartQuantityDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@Validated
public class OrderDTO {
    @NotNull(message = "Subsidiary Number must not be null")
    @Pattern(regexp="^[0-9]{1,8}$",message="Subsidiary Number must have to 8 digits")
    private String subsidiaryNumber;

    @NotNull(message = "Order Number must not be null")
    @Pattern(regexp="^[0-9]{8}$",message="Order Number must have 8 digits")
    private String orderNumber;

    @NotNull(message = "Parts must not be null")
    List<PartQuantityDTO> parts;

    public String getSubsidiaryNumber() {
        return subsidiaryNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public List<PartQuantityDTO> getParts() {
        return parts;
    }

    public void setSubsidiaryNumber(String subsidiaryNumber) {
        this.subsidiaryNumber = subsidiaryNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setParts(List<PartQuantityDTO> parts) {
        this.parts = parts;
    }
}
