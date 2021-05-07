package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.OrderDEResponseDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDEResponseDTO> getOrders(Map<String, String> params) throws Exception;
}
