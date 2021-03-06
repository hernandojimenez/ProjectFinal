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
    private Date orderDate = new Date();

    @Column
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "shippingType_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private ShippingType shippingType;

    @Column(nullable = false)
    private Long shippingType_id;


    @ManyToOne
    @JoinColumn(name = "deliveryStatus_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private DeliveryStatus deliveryS;


    @Column(nullable = false)
    private Long deliveryStatus_id;


    @ManyToOne
    @JoinColumn(name = "carrier_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private Carrier carrier;


    @Column(nullable = false)
    private Long carrier_id;


    @ManyToOne
    @JoinColumn(name = "orderStatus_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private OrderStatus orderStatus;


    @Column(nullable = false)
    private Long orderStatus_id;

    @ManyToOne
    @JoinColumn(name = "orderType_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private OrderType orderType;

    @Column(nullable = false)
    private Long orderType_id;

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
                && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(deliveryS, order.deliveryS)
                && Objects.equals(orderDetail, order.orderDetail) && Objects.equals(subsidiary, order.subsidiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNumber,orderDate, deliveryDate, deliveryS, orderDetail, subsidiary);
    }

    public OrderCM() {
    }

    public OrderCM(Long id, String orderNumber, Date orderDate, Date deliveryDate, OrderStatus orderStatus, List<OrderDetail> ordersDetail) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.orderDetail = ordersDetail;
    }
    public void setShippingType_id(Long shippingType_id) {
        this.shippingType_id = shippingType_id;
    }

    public void setDeliveryStatus_id(Long deliveryStatus_id) {
        this.deliveryStatus_id = deliveryStatus_id;
    }

    public void setCarrier_id(Long carrier_id) {
        this.carrier_id = carrier_id;
    }

    public void setOrderStatus_id(Long orderStatus_id) {
        this.orderStatus_id = orderStatus_id;
    }

    public void setOrderType_id(Long orderType_id) {
        this.orderType_id = orderType_id;
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
