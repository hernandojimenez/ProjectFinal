package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_CM")
public class OrderCM {

    @Id
    @Column(name = "order_id", length = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Date deliveryDate;

    @Column(nullable = false, length = 1)
    private String deliveryStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private ShippingType shippingType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private DeliveryStatus deliveryS;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private Carrier carrier;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private OrderType orderType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "_id", nullable = false)
    @JsonBackReference
    private Subsidiary subsidiary;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCM order = (OrderCM) o;
        return Objects.equals(id, order.id) && Objects.equals(orderDate, order.orderDate)
                && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(deliveryStatus, order.deliveryStatus)
                && Objects.equals(orderDetail, order.orderDetail) && Objects.equals(subsidiary, order.subsidiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, deliveryDate, deliveryS, orderDetail, subsidiary);
    }

}