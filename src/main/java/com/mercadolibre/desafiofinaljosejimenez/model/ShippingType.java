package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;

@Entity
@Table(name = "shipping_type")
public class ShippingType {

    @Id
    @Column(name = "shippingType_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShippingType(Long id, int code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public ShippingType() {}

}