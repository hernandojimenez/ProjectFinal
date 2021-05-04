package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;

@Entity
@Table(name = "stock_CM")
public class StockCM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;

    public int getQuantity() {
        return quantity;
    }
}
