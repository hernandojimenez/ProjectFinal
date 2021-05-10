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
    @JoinColumn(name = "part_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Part part;

    @Column(nullable = false)
    private Long part_id;


    @ManyToOne
    @JoinColumn(name = "main_subsidiary_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private MainSubsidiary main_subsidiary;


    public StockCM() {

    }
    @Column(nullable = false)
    private Long main_subsidiary_id;

    public int getQuantity() {
        return quantity;
    }

    public StockCM(int quantity, Long part_id, Long main_subsidiary_id) {
        this.quantity = quantity;
        this.part_id = part_id;
        this.main_subsidiary_id = main_subsidiary_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
