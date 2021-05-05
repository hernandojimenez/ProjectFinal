package com.mercadolibre.desafiofinaljosejimenez.dtos.response;

import javax.persistence.OrderBy;
import java.util.Date;

public class PartResponseDTO {
    private int partCode;
    private String description;
    private String maker;
    private int quantity;
    private String discountType;
    private double normalPrice;
    private double urgentPrice;
    private int netWeight;
    private int longDimension;
    private int widthDimension;
    private int tallDimension;
    private Date lastModification;

    public PartResponseDTO(int partCode, String description, String maker, int quantity, String discountType, double normalPrice, double urgentPrice, int netWeight, int longDimension, int widthDimension, int tallDimension, Date lastModification) {
        this.partCode = partCode;
        this.description = description;
        this.maker = maker;
        this.quantity = quantity;
        this.discountType = discountType;
        this.normalPrice = normalPrice;
        this.urgentPrice = urgentPrice;
        this.netWeight = netWeight;
        this.longDimension = longDimension;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.lastModification = lastModification;
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

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public double getUrgentPrice() {
        return urgentPrice;
    }

    public void setUrgentPrice(double urgentPrice) {
        this.urgentPrice = urgentPrice;
    }

    public int getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(int netWeight) {
        this.netWeight = netWeight;
    }

    public int getLongDimension() {
        return longDimension;
    }

    public void setLongDimension(int longDimension) {
        this.longDimension = longDimension;
    }

    public int getWidthDimension() {
        return widthDimension;
    }

    public void setWidthDimension(int widthDimension) {
        this.widthDimension = widthDimension;
    }

    public int getTallDimension() {
        return tallDimension;
    }

    public void setTallDimension(int tallDimension) {
        this.tallDimension = tallDimension;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }
}
