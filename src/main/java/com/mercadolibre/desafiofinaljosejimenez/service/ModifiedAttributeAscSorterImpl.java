package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

public class ModifiedAttributeAscSorterImpl extends PartSorter {
    public ModifiedAttributeAscSorterImpl(PartRepository repository) {
        super(repository);
    }

    @Override
    public List<Part> findParts(Date date) {
        return repository.findByModifiedAttributeAsc(date);
    }
}
