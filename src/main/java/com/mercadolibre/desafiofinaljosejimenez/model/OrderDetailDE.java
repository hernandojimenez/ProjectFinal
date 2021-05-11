package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "order_detail_de")
public class OrderDetailDE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_de_id", nullable = false)
    @JsonBackReference
    private OrderDE order_de;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    @JsonBackReference
    private Part part;

    @ManyToOne
    @JoinColumn(name = "accountType_id", nullable = false)
    @JsonBackReference
    private AccountType accountType;

    @ManyToOne
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

    public OrderDetailDE(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderDetailDE() {}

    public OrderDetailDE(Long id, Part part, AccountType accountType, Integer quantity) {
        this.id = id;
        this.part = part;
        this.accountType = accountType;
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public OrderDE getOrder_de() {
        return order_de;
    }

    public void setOrder_de(OrderDE order_de) {
        this.order_de = order_de;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public PartStatus getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(PartStatus partStatus) {
        this.partStatus = partStatus;
    }
}
