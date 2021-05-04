package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;

@Entity
@Table(name = "discount_rates")
public class DiscountRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private double discount;

    @OneToOne(mappedBy = "discountRate")
    private PartRecord partRecord;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public PartRecord getPartRecord() {
        return partRecord;
    }
}
