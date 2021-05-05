package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;

@Entity
@Table(name = "stock_CM")
public class StockCM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;

    public int getQuantity() {
        return quantity;
    }
}
