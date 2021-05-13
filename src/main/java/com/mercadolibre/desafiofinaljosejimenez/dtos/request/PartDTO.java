package com.mercadolibre.desafiofinaljosejimenez.dtos.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validated
public class PartDTO {
    @NotNull(message = "Part Code must not be null")
    @Digits(integer=8,fraction = 0, message = "part code must have up to 8 numbers")
    private int partCode;

    @NotNull(message = "Description must not be null")
    @Pattern(regexp="^[A-Za-z1-9 A-Za-z1-9]{1,100}$",message="Description must have up to 100 characters")
    private String description;

    @NotNull(message = "Width Dimension must not be null")
    @Digits(integer=4,fraction = 0, message = "Width dimension must have up to 4 numbers")
    private int widthDimension;

    @NotNull(message = "Tall Dimension must not be null")
    @Digits(integer=4,fraction = 0, message = "Tall dimension must have up to 4 numbers")
    private int tallDimension;

    @NotNull(message = "Long Dimension must not be null")
    @Digits(integer=4,fraction = 0, message = "Long dimension must have up to 4 numbers")
    private int longDimension;

    @NotNull(message = "NetWeight must not be null")
    @Digits(integer=5,fraction = 0, message = "Net Weight must have up to 5 numbers")
    private int netWeight;

    @NotNull(message = "Stock must not be null")
    @Digits(integer=8, fraction = 0, message ="Stock must have up to 8 numbers")
    private int stock;

    @NotNull(message = "Provider Name must not be null")
    @Pattern(regexp="^[A-Za-z1-9 A-Za-z1-9]{1,100}$",message="Provider Name must have up to 100 characters")
    private String providerName;

    public PartDTO(@Digits(integer = 8, fraction = 0, message = "part code must have up to 8 numbers") int partCode, @Pattern(regexp = "^[A-Za-z1-9 A-Za-z1-9]{1,100}$", message = "Description must have up to 100 characters") String description, @Digits(integer = 4, fraction = 0, message = "Width dimension must have up to 4 numbers") int widthDimension, @Digits(integer = 4, fraction = 0, message = "Tall dimension must have up to 4 numbers") int tallDimension, @Digits(integer = 4, fraction = 0, message = "Long dimension must have up to 4 numbers") int longDimension, @Digits(integer = 5, fraction = 0, message = "Net Weight must have up to 5 numbers") int netWeight, @Digits(integer = 8, fraction = 0, message = "Stock must have up to 8 numbers") int stock, @Pattern(regexp = "^[A-Za-z1-9 A-Za-z1-9]{1,100}$", message = "Provider Name must have up to 100 characters") String providerName) {
        this.partCode = partCode;
        this.description = description;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.longDimension = longDimension;
        this.netWeight = netWeight;
        this.stock = stock;
        this.providerName = providerName;
    }

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








