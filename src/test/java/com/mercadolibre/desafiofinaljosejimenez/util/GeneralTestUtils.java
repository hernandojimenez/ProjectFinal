package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneralTestUtils {
    public static List<PartResponseDTO> getParts() {
        List<PartResponseDTO> parts = new ArrayList<PartResponseDTO>();

        parts.add(new PartResponseDTO(12345678, "Motor V8", "Ford" , 4 , "A20", 2000, 2500, 60, 80, 80, 80, new Date()));
        parts.add(new PartResponseDTO(87654321, "Caja de cambios", "Fiat" , 48 , "A25", 1000, 1300, 20, 40, 30, 50, new Date()));
        parts.add(new PartResponseDTO(11223344, "Llantas", "Chevrolet" , 80 , "A20", 800, 1000, 50, 100, 100, 20, new Date()));

        return parts;
    }

    public static List<PartResponseDTO> getPartsWithFilters() {
        List<PartResponseDTO> parts = new ArrayList<PartResponseDTO>();

        parts.add(new PartResponseDTO(12345678, "Motor V8", "Ford" , 4 , "A20", 2000, 2500, 60, 80, 80, 80, new Date()));
        parts.add(new PartResponseDTO(11223344, "Llantas", "Chevrolet" , 80 , "A20", 800, 1000, 50, 100, 100, 20, new Date()));

        return parts;
    }
}
