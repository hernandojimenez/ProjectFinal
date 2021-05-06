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

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public Set<PartRecord> getPartRecords() {
        return partRecords;
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
