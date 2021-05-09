package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "discountRate",cascade = CascadeType.ALL)
    private Set<PartRecord> partRecords;

    public DiscountRate() {

    }

    public String getDescription() {
        return description;
    }

    public DiscountRate(Long id, String description, double discount) {
        this.id = id;
        this.description = description;
        this.discount = discount;
    }
}
