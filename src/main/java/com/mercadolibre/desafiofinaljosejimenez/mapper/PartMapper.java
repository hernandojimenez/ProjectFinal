package com.mercadolibre.desafiofinaljosejimenez.mapper;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.PartRecord;

import java.util.Optional;

public class PartMapper {

   public static PartResponseDTO mapPartToResponse(Part part){
        if(part!=null){
            Optional<PartRecord> lastPart = part.getPartRecords().stream().findFirst();
            if(lastPart.isPresent()){
                return new PartResponseDTO(part.getPartCode(),part.getDescription(),part.getProvider().getName(),part.getStockCM().getQuantity(),
                        lastPart.get().getDiscountRate().getDescription(),lastPart.get().getNormalPrice(),lastPart.get().getUrgentPrice(),
                        part.getNetWeight(),part.getLongDimension(), part.getWidthDimension(), part.getTallDimension(),lastPart.get().getLastModification());
            }

        }
        return null;
    }
}
