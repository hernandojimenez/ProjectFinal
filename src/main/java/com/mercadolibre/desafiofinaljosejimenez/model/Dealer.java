package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dealers")
public class Dealer {
    @Id
    @Column(name = "dealer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dealerNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.PERSIST)
    private Set<OrderDE> orderDE;

    @ManyToOne
    @JoinColumn(name = "subsidiary_id", nullable = false)
    @JsonBackReference
    private Subsidiary subsidiary;

    public Dealer(String dealerNumber, Subsidiary subsidiary) {
        this.dealerNumber = dealerNumber;
        this.subsidiary = subsidiary;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
}
