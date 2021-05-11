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

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private OrderCM order;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    @JsonBackReference
    private Part part;

    @ManyToOne
    @JoinColumn(name = "accountType_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private AccountType accountType;



    @Column(nullable = false)
    private Long accountType_id;

    @ManyToOne
    @JoinColumn(name = "partStatus_id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private PartStatus partStatus;

    @Column(nullable = false)
    private Long partStatus_id;

    @Column(nullable = false)
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderCM getOrder() {
        return order;
    }

    public Part getPart() {
        return part;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public PartStatus getPartStatus() {
        return partStatus;
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

    public void setOrder(OrderCM order) {
        this.order = order;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public Long getAccountType_id() {
        return accountType_id;
    }

    public void setAccountType_id(Long accountType_id) {
        this.accountType_id = accountType_id;
    }
    public void setPartStatus(PartStatus partStatus) {
        this.partStatus = partStatus;
    }
    public Long getPartStatus_id() {
        return partStatus_id;
    }

    public void setPartStatus_id(Long partStatus_id) {
        this.partStatus_id = partStatus_id;
    }
}
