package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.PartRecord;

import java.util.List;
import java.util.Map;

public interface PartService {

    List<PartResponseDTO> getParts(Map<String, String> params) throws Exception;

    String savePart(PartDTO part);
}
