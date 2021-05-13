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
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartSorterUtilsTest {
    @Mock
    private PartRepository partRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Gets all the parts with no filters applied")
    public void getAllParts() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        when(partRepository.findAll()).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(new HashMap<>(), partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }

    @Test
    @DisplayName("Gets all the parts with query type 'C'")
    public void getPartsQueryTypeC() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        Map<String, String> params = new HashMap<>();

        params.put("queryType", "C");

        when(partRepository.findAll()).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(params, partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }

    @Test
    @DisplayName("Gets all the parts with query type 'P' asc")
    public void getPartsQueryTypePAsc() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        Map<String, String> params = new HashMap<>();

        params.put("queryType", "P");
        params.put("order", "1");

        when(partRepository.findByModifiedAttributeAsc(any())).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(params, partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }

    @Test
    @DisplayName("Gets all the parts with query type 'P' desc")
    public void getPartsQueryTypePDesc() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        Map<String, String> params = new HashMap<>();

        params.put("queryType", "P");
        params.put("order", "2");

        when(partRepository.findByModifiedAttributeDesc(any())).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(params, partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }

    @Test
    @DisplayName("Gets all the parts with query type 'V' asc")
    public void getPartsQueryTypeVAsc() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        Map<String, String> params = new HashMap<>();

        params.put("queryType", "V");
        params.put("order", "1");

        when(partRepository.findByModifiedPriceAsc(any())).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(params, partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }

    @Test
    @DisplayName("Gets all the parts with query type 'V' desc")
    public void getPartsQueryTypeVDesc() throws Exception {
        List<Part> partsDB = GeneralTestUtils.getPartsDB();
        List<PartResponseDTO> parts = GeneralTestUtils.getParts();

        Map<String, String> params = new HashMap<>();

        params.put("queryType", "V");
        params.put("order", "2");

        when(partRepository.findByModifiedPriceDesc(any())).thenReturn(partsDB);

        List<Part> responseParts = PartSorterUtils.getSorter(params, partRepository, new Date());

        Assertions.assertEquals(parts.size(), responseParts.size());
    }
}
