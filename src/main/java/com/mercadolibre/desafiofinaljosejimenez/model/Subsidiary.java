package com.mercadolibre.desafiofinaljosejimenez.model;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "subsidiary")
public class Subsidiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subsidiary_id", length = 4)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String country;

    @Column(name = "days_to_shipping", nullable = false)
    private Integer daysToShipping;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<OrderCM> orderCm;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<SubsidiaryStock> subsidiaryStocks;

    //@OneToMany(mappedBy = "subsidiary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<UserCentral> users;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Dealer> dealers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subsidiary that = (Subsidiary) o;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address)
                && Objects.equals(phone, that.phone) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone, country);
    }
}
