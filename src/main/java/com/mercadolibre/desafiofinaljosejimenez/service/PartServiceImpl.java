package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.mapper.PartMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    public List<PartResponseDTO> getParts(Map<String, String> params) {
        // Validar parametros

        List<Part> dbParts = new ArrayList<>();

        String queryType = params.get("querytype");

        if (params.isEmpty() || queryType.equals("C"))
            dbParts = repository.findAll();
        else if (queryType.equals("P")) {
            Date daySelected = DateUtils.getDateFromString(params.get("date"));

            if (params.get("order").equals("1"))
                dbParts = repository.findByModifiedAttributeAsc(daySelected);
            else if (params.get("order").equals("2"))
                dbParts = repository.findByModifiedAttributeDesc(daySelected);
        }
        else if (queryType.equals("V")) {
            Date daySelected = DateUtils.getDateFromString(params.get("date"));

            if (params.get("order").equals("1"))
                dbParts = repository.findByModifiedPriceAsc(daySelected);
            else if (params.get("order").equals("2"))
                dbParts = repository.findByModifiedPriceDesc(daySelected);
        }

        List<PartResponseDTO> result = dbParts.stream().map(part -> PartMapper.mapPartToResponse(part)).collect(Collectors.toList());

        return result;
    }
}
