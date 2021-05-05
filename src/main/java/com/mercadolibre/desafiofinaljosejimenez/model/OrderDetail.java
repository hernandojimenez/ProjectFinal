package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_type_id", nullable = false)
    @JsonBackReference
    private AccountType accountType;


    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 100)
    private String reason;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "part_id", nullable = false)
    @JsonBackReference
    private Part partOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private OrderCM order;
}
