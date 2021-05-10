package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import javax.persistence.Column;

public class PartDTO {
    private int partCode;
    private String description;
    private int widthDimension;
    private int tallDimension;
    private int longDimension;
    private int netWeight;
    private int stock;
    private String providerName;

    public int getPartCode() {
        return partCode;
    }

    public String getDescription() {
        return description;
    }

    public int getWidthDimension() {
        return widthDimension;
    }

    public int getTallDimension() {
        return tallDimension;
    }

    public int getLongDimension() {
        return longDimension;
    }

    public int getNetWeight() {
        return netWeight;
    }

    public int getStock() {
        return stock;
    }

    public String getProviderName() {
        return providerName;
    }
}
