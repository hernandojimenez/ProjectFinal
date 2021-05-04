package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;

import java.util.List;

public interface PartService {

    List<PartResponseDTO> getParts();
}
