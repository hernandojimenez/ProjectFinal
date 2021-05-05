package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;

@Entity
@Table (name = "account_type")
public class AccountType {

    @Id
    @Column(name = "accountType_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

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
