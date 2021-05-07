package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserCentral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subsidiary_id", nullable = true, updatable = false, insertable = false)
    @JsonBackReference
    private Subsidiary subsidiary;

    @Column(nullable = true)
    private Long subsidiary_id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "main_subsidiary_id", nullable = true, updatable = false, insertable = false)
    @JsonBackReference
    private MainSubsidiary main_subsidiary;

    @Column(nullable = true)
    private Long main_subsidiary_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSubsidiary_id() {
        return subsidiary_id;
    }

    public void setSubsidiary_id(Long subsidiary_id) {
        this.subsidiary_id = subsidiary_id;
    }

    public Long getMain_subsidiary_id() {
        return main_subsidiary_id;
    }

    public void setMain_subsidiary_id(Long main_subsidiary_id) {
        this.main_subsidiary_id = main_subsidiary_id;
    }
}
