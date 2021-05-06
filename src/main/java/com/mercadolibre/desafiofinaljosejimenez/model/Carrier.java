package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "carriers")
public class Carrier {

    @Id
    @Column(name = "carrier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.PERSIST)
    private Set<OrderCM> orderCM;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Carrier(Long id, String name, String address, String phone, String country) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.country = country;
    }
}
