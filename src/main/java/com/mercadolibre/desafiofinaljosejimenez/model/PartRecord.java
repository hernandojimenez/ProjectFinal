package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "part_records")
public class PartRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date lastModification;

    private double normalPrice;

    private double salePrice;

    private double urgentPrice;

    private String modifiedField;

    @ManyToOne
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discountRate_id", referencedColumnName = "id")
    private DiscountRate discountRate;

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
}
