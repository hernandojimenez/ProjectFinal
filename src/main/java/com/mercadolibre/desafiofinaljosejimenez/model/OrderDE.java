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

    @Column(nullable = false, length = 8)
    private String orderNumber;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Date deliveryDate;

    //@Column(nullable = false, length = 1)
    //private String deliveryStatus;

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

    public OrderDE(Long id, Date orderDate, Date deliveryDate, DeliveryStatus deliveryS, OrderStatus orderStatus, List<OrderDetailDE> orderDetailDE) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryS = deliveryS;
        this.orderStatus = orderStatus;
        this.orderDetailDE = orderDetailDE;
    }

    public DeliveryStatus getDeliveryS() {
        return deliveryS;
    }

    public void setDeliveryS(DeliveryStatus deliveryS) {
        this.deliveryS = deliveryS;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<OrderDetailDE> getOrderDetailDE() {
        return orderDetailDE;
    }

    public void setOrderDetailDE(List<OrderDetailDE> orderDetailDE) {
        this.orderDetailDE = orderDetailDE;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
