package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "stock_CM")
public class StockCM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;


    @ManyToOne
    @JoinColumn(name = "main_subsidiary_id", nullable = false)
    @JsonBackReference
    private MainSubsidiary main_subsidiary;

    public StockCM() {

    }

    public int getQuantity() {
        return quantity;
    }

    public StockCM(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
