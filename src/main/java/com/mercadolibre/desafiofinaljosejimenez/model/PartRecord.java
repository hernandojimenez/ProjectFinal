package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "part_records")
public class PartRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    private Date lastModification;

    @Column(nullable = false)
    private double normalPrice;

    @Column(nullable = false)
    private double salePrice;

    @Column(nullable = false)
    private double urgentPrice;

    @Column(nullable = false)
    private String modifiedField;

    @ManyToOne
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;


    @ManyToOne
    @JoinColumn(name = "discountRate_id", referencedColumnName = "id")
    private DiscountRate discountRate;

    public PartRecord() {

    }

    public Date getLastModification() {
        return lastModification;
    }

    public double getNormalPrice() {
        return normalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getUrgentPrice() {
        return urgentPrice;
    }

    public Part getPart() {
        return part;
    }

    public DiscountRate getDiscountRate() {
        return discountRate;
    }

    public PartRecord(Long id, Date lastModification, double normalPrice, double salePrice, double urgentPrice, String modifiedField, DiscountRate discountRate) {
        this.id = id;
        this.lastModification = lastModification;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.urgentPrice = urgentPrice;
        this.modifiedField = modifiedField;
        this.discountRate = discountRate;
    }


}
