package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "dealer_stock")
public class DealerStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    private Integer quantity;

    @JoinColumn(name = "part_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private Part part;

    @JoinColumn(name = "dealer_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private Dealer dealer;


    public DealerStock(Long id, Integer quantity, Part part, Dealer dealer) {
        this.id = id;
        this.quantity = quantity;
        this.part = part;
        this.dealer = dealer;
    }

    public DealerStock() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
