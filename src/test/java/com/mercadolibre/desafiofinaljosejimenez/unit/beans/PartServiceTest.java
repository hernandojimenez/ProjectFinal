package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.NotFoundException;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.service.PartServiceImpl;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartServiceTest {
    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private PartServiceImpl partService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Gets all the parts with no filters applied")
    public void getAllParts() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        when(partRepository.findAll()).thenReturn(partsDB);

        List<PartResponseDTO> responseParts = partService.getParts(new HashMap<>());

        Assertions.assertEquals(parts.size(), responseParts.size());
        Assertions.assertEquals(parts.get(0).getPartCode(), responseParts.get(0).getPartCode());
        Assertions.assertEquals(parts.get(0).getMaker(), responseParts.get(0).getMaker());
    }

    @Test
    @DisplayName("Gets an exception because it could not find any parts")
    public void partsNotFoundException() throws Exception {
        List<Part> partsDB = new ArrayList<>();

        when(partRepository.findByModifiedAttributeDesc(any())).thenReturn(partsDB);

        try {
            List<PartResponseDTO> responseParts = partService.getParts(new HashMap<>());
        }
        catch (NotFoundException e) {
            Assertions.assertTrue(e.getMessage().contains("404 Not Found"));
        }
    }
}
