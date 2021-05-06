package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponse;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.mapper.PartMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService{
    @Override
    public List<OrderDEResponse> getOrders(Map<String, String> params) throws Exception {
        //Validator.validFiltersOrders(params);

        //PartSorter sorter = SorterUtils.getSorterOrder(params, repository);

        //List<OrderDE> dbParts = sorter.findParts(daySelected);

        //List<OrderDEResponse> result = dbParts.stream().map(part -> { return PartMapper.mapPartToResponse(part); }).collect(Collectors.toList());

        //return result;

        return null;
    }
}
