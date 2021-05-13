package com.mercadolibre.desafiofinaljosejimenez.unit.beans;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.NotFoundException;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.Provider;
import com.mercadolibre.desafiofinaljosejimenez.model.StockCM;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.ProviderRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.StockCMRepository;
import com.mercadolibre.desafiofinaljosejimenez.service.PartServiceImpl;
import com.mercadolibre.desafiofinaljosejimenez.util.GeneralTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PartServiceTest {
    @Mock
    private PartRepository partRepository;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private StockCMRepository stockCMRepository;

    @InjectMocks
    private PartServiceImpl partService;

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

    @Test
    @DisplayName("Saves a part to the database")
    public void savePart() {
        PartDTO part = GeneralTestUtils.getPartsDTO().get(0);

        when(partRepository.findByPartCode(12345678)).thenReturn(null);
        when(providerRepository.findByName(any())).thenReturn(new Provider(3L, "Buenos Aires"));
        when(partRepository.save(any())).thenReturn(new Part(1L));
        when(stockCMRepository.save(any())).thenReturn(new StockCM());

        String message = partService.savePart(part);

        Assertions.assertTrue(message.contains("The part was added to the inventory successfully"));
    }

    @Test
    @DisplayName("Updates stock of a part")
    public void updatePartStock() {
        PartDTO part = GeneralTestUtils.getPartsDTO().get(1);

        when(partRepository.findByPartCode(10000010)).thenReturn(new Part(2L));
        when(stockCMRepository.findByPart_id(any())).thenReturn(new StockCM(10, 1L, 1L));
        when(stockCMRepository.save(any())).thenReturn(new StockCM());

        String message = partService.savePart(part);

        Assertions.assertTrue(message.contains("The stock of the part was updated successfully"));
    }
}
