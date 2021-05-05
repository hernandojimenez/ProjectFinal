package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Objects;

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

    @OneToOne(mappedBy = "discountRate",cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountRate that = (DiscountRate) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, discount);
    }
}
