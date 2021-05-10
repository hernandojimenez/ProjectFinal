package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.*;

import java.util.*;

public class GeneralTestUtils {
    public static List<PartResponseDTO> getParts() {
        List<PartResponseDTO> parts = new ArrayList<PartResponseDTO>();

        parts.add(new PartResponseDTO(12345678, "Motor V8", "Ford" , 4 , "A20", 2000, 2500, 60, 80, 80, 80, "2021-01-01"));
        parts.add(new PartResponseDTO(87654321, "Caja de cambios", "Fiat" , 48 , "A25", 1000, 1300, 20, 40, 30, 50, "2021-02-02"));
        parts.add(new PartResponseDTO(11223344, "Llantas", "Chevrolet" , 80 , "A20", 800, 1000, 50, 100, 100, 20, "2021-03-03"));

        return parts;
    }

    public static List<PartResponseDTO> getPartsWithFilters() {
        List<PartResponseDTO> parts = new ArrayList<PartResponseDTO>();

        parts.add(new PartResponseDTO(12345678, "Motor V8", "Ford" , 4 , "A20", 2000, 2500, 60, 80, 80, 80, "2021-01-01"));
        parts.add(new PartResponseDTO(11223344, "Llantas", "Chevrolet" , 80 , "A20", 800, 1000, 50, 100, 100, 20, "2021-03-03"));

        return parts;
    }

    public static List<Part> getPartsDB() {
        List<Part> parts = new ArrayList<Part>();

        Set<PartRecord> partRecordSet1 = new HashSet<>();
        partRecordSet1.add(new PartRecord(1L, new Date(), 2000, 1500, 2500, "normal_price", new DiscountRate(1L, "A20", 0.25)));

        Set<PartRecord> partRecordSet2 = new HashSet<>();
        partRecordSet2.add(new PartRecord(2L, new Date(), 1000, 800, 1300, "normal_price", new DiscountRate(1L, "A25", 0.25)));

        Set<PartRecord> partRecordSet3 = new HashSet<>();
        partRecordSet3.add(new PartRecord(3L, new Date(), 800, 700, 100, "normal_price", new DiscountRate(1L, "A20", 0.25)));

        parts.add(new Part(1L, 12345678, "Motor V8", 80, 80, 80, 60, new StockCM(4, 1L, 1L), new Provider(1L, "Ford"), partRecordSet1));
        parts.add(new Part(2L, 87654321, "Caja de cambios", 30, 50, 40, 20, new StockCM(48, 1L, 1L), new Provider(1L, "Fiat"), partRecordSet2));
        parts.add(new Part(3L, 11223344, "Llantas", 100, 20, 100, 50, new StockCM(80, 1L, 1L), new Provider(1L, "Chevrolet"), partRecordSet3));

        return parts;
    }
}
