package com.mercadolibre.desafiofinaljosejimenez.model;

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
    private String subsidiaryNumber;

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

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserCentral> users;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Dealer> dealers;

    public Subsidiary(Long central) {
        this.id=central;
    }

    public Subsidiary() {

    }

    public Subsidiary(String subsidiaryNumber) {
        this.subsidiaryNumber = subsidiaryNumber;
    }

    public Subsidiary(Long id, String subsidiaryNumber, String name, String address, String phone, String country, Integer daysToShipping) {
        this.id = id;
        this.subsidiaryNumber = subsidiaryNumber;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.daysToShipping = daysToShipping;
    }

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

    public String getSubsidiaryNumber() {
        return subsidiaryNumber;
    }

    public void setSubsidiaryNumber(String subsidiaryNumber) {
        this.subsidiaryNumber = subsidiaryNumber;
    }

    public Set<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(Set<Dealer> dealers) {
        this.dealers = dealers;
    }

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

    public Integer getDaysToShipping() {
        return daysToShipping;
    }

    public void setDaysToShipping(Integer daysToShipping) {
        this.daysToShipping = daysToShipping;
    }

    public Set<OrderCM> getOrderCm() {
        return orderCm;
    }

    public void setOrderCm(Set<OrderCM> orderCm) {
        this.orderCm = orderCm;
    }

    public List<SubsidiaryStock> getSubsidiaryStocks() {
        return subsidiaryStocks;
    }

    public void setSubsidiaryStocks(List<SubsidiaryStock> subsidiaryStocks) {
        this.subsidiaryStocks = subsidiaryStocks;
    }

    public List<UserCentral> getUsers() {
        return users;
    }

    public void setUsers(List<UserCentral> users) {
        this.users = users;
    }
}
