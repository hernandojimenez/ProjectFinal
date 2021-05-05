package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;

import java.util.List;
import java.util.Map;

public interface PartService {

    List<PartResponseDTO> getParts(Map<String, String> params);
}
