package com.mercadolibre.desafiofinaljosejimenez.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JoinColumn(name = "provider_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private Provider provider;

    @Column(nullable = false)
    private Long provider_id;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<PartRecord> partRecords;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private Set<SubsidiaryStock> subsidiaryStock;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private Set<OrderDetailDE> orderDetailsDE;

    public Part() {

    }

    public Part(Long id) {
        this.id = id;
    }

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

    public Part(Long id, int partCode, String description, int widthDimension, int tallDimension, int longDimension, int netWeight, StockCM stockCM, Provider provider, Set<PartRecord> partRecords) {
        this.id = id;
        this.partCode = partCode;
        this.description = description;
        this.widthDimension = widthDimension;
        this.tallDimension = tallDimension;
        this.longDimension = longDimension;
        this.netWeight = netWeight;
        this.stockCM = stockCM;
        this.provider = provider;
        this.partRecords = partRecords;
    }

    public void setProvider_id(Long provider_id) {
        this.provider_id = provider_id;
    }
}
