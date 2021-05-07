package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.mapper.OrderMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.OrderDE;
import com.mercadolibre.desafiofinaljosejimenez.repositories.OrderRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.OrderSorterUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;

    ModelMapper mapper;

    public OrderServiceImpl(OrderRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public OrderDEResponseDTO getOrders(Map<String, String> params) throws Exception {

        Validator.validFiltersOrders(params);

        List<OrderDE> dbOrders  = OrderSorterUtils.getSortedList(params, repository);

        List<OrderResponseDTO> result = dbOrders.stream().map(order -> { return OrderMapper.mapOrderToResponse(order); }).collect(Collectors.toList());

        return new OrderDEResponseDTO(params.get("dealerNumber"),result);

    }
}
