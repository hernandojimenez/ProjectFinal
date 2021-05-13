package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "account_type")
public class AccountType {

    @Id
    @Column(name = "accountType_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1)
    private String description;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.PERSIST)
    private Set<OrderDetailDE> orderDetailsDE;

    public AccountType() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountType(Long id, String description) {
        this.id = id;
        this.description = description;
    }




}
