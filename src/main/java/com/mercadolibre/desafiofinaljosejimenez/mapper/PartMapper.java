package com.mercadolibre.desafiofinaljosejimenez.mapper;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.PartRecord;
import com.mercadolibre.desafiofinaljosejimenez.util.DateUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PartMapper {

   public static PartResponseDTO mapPartToResponse(Part part){
        if (part != null) {
            // Orders by last_modification desc
            List<PartRecord> partsList = part.getPartRecords().stream().sorted((pr1, pr2) ->
                    pr2.getLastModification().compareTo(pr1.getLastModification())).collect(Collectors.toList());

            PartRecord lastPart = partsList.get(0);

            return new PartResponseDTO(part.getPartCode(),part.getDescription(),part.getProvider().getName(),part.getStockCM().getQuantity(),
                    lastPart.getDiscountRate().getDescription(),lastPart.getNormalPrice(),lastPart.getUrgentPrice(),
                    part.getNetWeight(),part.getLongDimension(), part.getWidthDimension(), part.getTallDimension(), DateUtils.dateToString(lastPart.getLastModification()));
        }

        return null;
    }

    public static Part MapPartDTOToPart(PartDTO partDTO) {
       return new Part(null, partDTO.getPartCode(), partDTO.getDescription(), partDTO.getWidthDimension(), partDTO.getTallDimension(),
               partDTO.getLongDimension(), partDTO.getNetWeight(), null, null, null);
    }
}
