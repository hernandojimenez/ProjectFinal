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

    @Column(nullable = false, length = 8)
    private String orderNumber;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private Date deliveryDate;

    @Column(nullable = false, length = 1)
    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "shippingType_id", nullable = false)
    @JsonBackReference
    private ShippingType shippingType;

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
    @JoinColumn(name = "subsidiary_id", nullable = false)
    @JsonBackReference
    private Subsidiary subsidiary;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCM order = (OrderCM) o;
        return Objects.equals(id, order.id) && Objects.equals(orderNumber,order.orderNumber) && Objects.equals(orderDate, order.orderDate)
                && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(deliveryStatus, order.deliveryStatus)
                && Objects.equals(orderDetail, order.orderDetail) && Objects.equals(subsidiary, order.subsidiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber,orderDate, deliveryDate, deliveryS, orderDetail, subsidiary);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public DeliveryStatus getDeliveryS() {
        return deliveryS;
    }

    public void setDeliveryS(DeliveryStatus deliveryS) {
        this.deliveryS = deliveryS;
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

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
