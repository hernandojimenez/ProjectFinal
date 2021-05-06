package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_DE")
public class OrderDE {

    @Id
    @Column(name = "order_de_id", length = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Date deliveryDate;

    @Column(nullable = false, length = 1)
    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "deliveryStatus_id", nullable = false)
    @JsonBackReference
    private DeliveryStatus deliveryS;

    @ManyToOne
    @JoinColumn(name = "carrier_id", nullable = false)
    @JsonBackReference
    private Carrier carrier;

    @ManyToOne
    @JoinColumn(name = "orderStatus_id", nullable = false)
    @JsonBackReference
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "orderType_id", nullable = false)
    @JsonBackReference
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    @JsonBackReference
    private Dealer dealer;

    @OneToMany(mappedBy = "order_de", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderDetailDE> orderDetailDE;
}
