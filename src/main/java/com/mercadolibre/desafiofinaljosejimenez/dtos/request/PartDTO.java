package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Validated
public class PartDTO {

    @Digits(integer=8,fraction = 0, message = "part code must have up to 8 numbers")
    private int partCode;

    @Pattern(regexp="^[A-Za-z1-9 A-Za-z1-9]{1,100}$",message="Description must have up to 100 characters")
    private String description;

    @Digits(integer=4,fraction = 0, message = "Width dimension must have up to 4 numbers")
    private int widthDimension;

    @Digits(integer=4,fraction = 0, message = "Tall dimension must have up to 4 numbers")
    private int tallDimension;

    @Digits(integer=4,fraction = 0, message = "Long dimension must have up to 4 numbers")
    private int longDimension;

    @Digits(integer=5,fraction = 0, message = "Net Weight must have up to 5 numbers")
    private int netWeight;

    @Digits(integer=8, fraction = 0, message ="Stock must have up to 8 numbers")
    private int stock;

    @Pattern(regexp="^[A-Za-z1-9 A-Za-z1-9]{1,100}$",message="Provider Name must have up to 100 characters")
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








