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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private OrderCM order;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "part_id", nullable = false)
    @JsonBackReference
    private Part part;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_type_id", nullable = false)
    @JsonBackReference
    private AccountType accountType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "partStatus_id", nullable = false)
    @JsonBackReference
    private PartStatus partStatus;

    @Column(nullable = false)
    private Integer quantity;

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

    public OrderDetail(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderDetail() {}
}
