package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import org.apache.commons.lang.NotImplementedException;

import java.util.Date;
import java.util.List;

public class PartSorter {
    protected final PartRepository repository;

    public PartSorter(PartRepository repository) {
        this.repository = repository;
    }

    List<Part> findParts(Date date) {
        throw new NotImplementedException();
    }
}
