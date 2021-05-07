package com.mercadolibre.desafiofinaljosejimenez.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String country;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    private Set<Part> parts;

    public String getName() {
        return name;
    }

    public Provider(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
