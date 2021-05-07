package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "main_subsidiary")
public class MainSubsidiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_subsidiary_id", length = 4)
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


    @OneToMany(mappedBy = "main_subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<StockCM> stockCM;

    @OneToMany(mappedBy = "main_subsidiary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserCentral> users;

    public MainSubsidiary(Long idCentral) {
    }

    public MainSubsidiary() {

    }


    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone, country);
    }



}
