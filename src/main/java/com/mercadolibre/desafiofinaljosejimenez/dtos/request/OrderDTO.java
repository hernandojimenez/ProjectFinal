package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import com.mercadolibre.desafiofinaljosejimenez.dtos.internal.PartQuantityDTO;

import java.util.List;

public class OrderDTO {
    private String subsidiaryNumber;
    List<PartQuantityDTO> parts;

    public String getSubsidiaryNumber() {
        return subsidiaryNumber;
    }

    public List<PartQuantityDTO> getPartCodes() {
        return parts;
    }
}
