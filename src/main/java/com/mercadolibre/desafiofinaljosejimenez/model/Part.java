package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "part_code", nullable = false, length = 8)
    private int partCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int widthDimension;

    @Column(nullable = false)
    private int tallDimension;

    @Column(nullable = false)
    private int longDimension;

    @Column(nullable = false)
    private int netWeight;

    @OneToOne(mappedBy = "part",cascade = CascadeType.ALL)
    private StockCM stockCM;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    @JsonBackReference
    private Provider provider;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<PartRecord> partRecords;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private Set<SubsidiaryStock> subsidiaryStock;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private Set<OrderDetailDE> orderDetailsDE;

    public Long getId() {
        return id;
    }

    public int getPartCode() {
        return partCode;
    }

    public String getDescription() {
        return description;
    }

    public int getWidthDimension() {
        return widthDimension;
    }

    public int getTallDimension() {
        return tallDimension;
    }

    public int getLongDimension() {
        return longDimension;
    }

    public int getNetWeight() {
        return netWeight;
    }

    public StockCM getStockCM() {
        return stockCM;
    }

    public Provider getProvider() {
        return provider;
    }

    public Set<PartRecord> getPartRecords() {
        return partRecords;
    }



}
