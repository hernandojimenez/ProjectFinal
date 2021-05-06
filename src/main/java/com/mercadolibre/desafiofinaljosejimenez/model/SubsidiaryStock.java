package com.mercadolibre.desafiofinaljosejimenez.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "subsidiary_stock")
public class SubsidiaryStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    private Integer quantity;

    @JoinColumn(name = "part_id", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonBackReference
    private Part part;

    @JoinColumn(name = "subsidiary_id", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonBackReference
    private Subsidiary subsidiary;


    public SubsidiaryStock(Long id, Integer quantity, Part part, Subsidiary subsidiary) {
        this.id = id;
        this.quantity = quantity;
        this.part = part;
        this.subsidiary = subsidiary;
    }

    public SubsidiaryStock () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
}
