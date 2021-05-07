package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.PartSorterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartSorterUtilsTest {
    @Mock
    private PartRepository partRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Gets all the parts with no filters applied")
    public void getAllParts() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        when(partRepository.findByModifiedAttributeDesc(any())).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(new HashMap<>(), partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }
}
