package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.mapper.PartMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService{

    private final PartRepository repository;

    ModelMapper mapper;

    public PartServiceImpl(PartRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PartResponseDTO> getParts() {
        List<Part> db =repository.findAll();
        List<PartResponseDTO> result=db.stream().map(part -> PartMapper.mapPartToResponse(part)).collect(Collectors.toList());
        return result;
    }
}
