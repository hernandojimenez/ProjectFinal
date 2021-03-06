package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "part_status")
public class PartStatus {

    @Id
    @Column(name = "partStatus_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private int code;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToMany(mappedBy = "partStatus", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "partStatus", cascade = CascadeType.PERSIST)
    private Set<OrderDetailDE> orderDetailsDE;

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

    public PartStatus(Long id, int code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public PartStatus() {}
}
