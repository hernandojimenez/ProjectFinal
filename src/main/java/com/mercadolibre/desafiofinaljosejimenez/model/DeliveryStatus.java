package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "delivery_status")
public class DeliveryStatus {

    @Id
    @Column(name = "deliveryStatus_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "deliveryS", cascade = CascadeType.PERSIST)
    private Set<OrderCM> orderCM;

    @OneToMany(mappedBy = "deliveryS", cascade = CascadeType.PERSIST)
    private Set<OrderDE> orderDE;


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

    public DeliveryStatus(Long id, int code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public DeliveryStatus() {}
}
