package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "part_code")
    private int partCode;

    @Column
    private String description;

    @Column
    private int widthDimension;

    @Column
    private int tallDimension;

    @Column
    private int longDimension;

    @Column
    private int netWeight;

    @OneToOne(mappedBy = "part")
    private StockCM stockCM;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @OneToMany(mappedBy = "part")
    private Set<PartRecord> partRecords;

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
