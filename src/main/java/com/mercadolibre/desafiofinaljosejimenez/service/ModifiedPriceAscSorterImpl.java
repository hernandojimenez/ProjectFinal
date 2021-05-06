package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;

import java.util.Date;
import java.util.List;

public class ModifiedPriceAscSorterImpl extends PartSorter {
    public ModifiedPriceAscSorterImpl(PartRepository repository) {
        super(repository);
    }

    @Override
    public List<Part> findParts(Date date) {
        return repository.findByModifiedPriceAsc(date);
    }
}
