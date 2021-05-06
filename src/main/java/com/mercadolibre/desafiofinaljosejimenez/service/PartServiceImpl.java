package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.mapper.PartMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.PartRecord;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.DateUtils;

import com.mercadolibre.desafiofinaljosejimenez.util.SorterUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository repository;

    ModelMapper mapper;

    public PartServiceImpl(PartRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PartResponseDTO> getParts(Map<String, String> params) throws Exception {

        // Validar parametros
        Validator.validFilters(params);

        // if there is no date in params we create a new date
        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(today);

        String date = params.getOrDefault("date",  strDate);

        Date daySelected = DateUtils.getDateFromString(date);

        PartSorter sorter = SorterUtils.getSorter(params, repository);

        List<Part> dbParts = sorter.findParts(daySelected);

        List<PartResponseDTO> result = dbParts.stream().map(part -> { return PartMapper.mapPartToResponse(part); }).collect(Collectors.toList());

        return result;
    }
}
