package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponse;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDEResponse> getOrders(Map<String, String> params) throws Exception;
}
