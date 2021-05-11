package com.mercadolibre.desafiofinaljosejimenez.service;

import com.mercadolibre.desafiofinaljosejimenez.dtos.request.PartDTO;
import com.mercadolibre.desafiofinaljosejimenez.dtos.response.PartResponseDTO;
import com.mercadolibre.desafiofinaljosejimenez.exceptions.NotFoundException;
import com.mercadolibre.desafiofinaljosejimenez.mapper.PartMapper;
import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.Provider;
import com.mercadolibre.desafiofinaljosejimenez.model.StockCM;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.ProviderRepository;
import com.mercadolibre.desafiofinaljosejimenez.repositories.StockCMRepository;
import com.mercadolibre.desafiofinaljosejimenez.util.DateUtils;

import com.mercadolibre.desafiofinaljosejimenez.util.PartSorterUtils;
import com.mercadolibre.desafiofinaljosejimenez.util.Validator;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final StockCMRepository stockCMRepository;
    private final ProviderRepository providerRepository;

    public PartServiceImpl(PartRepository partRepository, StockCMRepository stockCMRepository, ProviderRepository providerRepository) {
        this.partRepository = partRepository;
        this.stockCMRepository = stockCMRepository;
        this.providerRepository = providerRepository;
    }

    @Override
    public List<PartResponseDTO> getParts(Map<String, String> params) throws Exception {
        // Validate parameters
        Validator.validFilters(params);

        // if there is no date in params we create a new date
        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(today);

        String date = params.getOrDefault("date",  strDate);

        Date daySelected = DateUtils.getDateFromString(date);

        List<Part> dbParts = PartSorterUtils.getSorter(params, partRepository,daySelected);

        if (!dbParts.isEmpty()){
            List<PartResponseDTO> result = new ArrayList<>();
            dbParts.stream().forEach(part -> { if (!part.getPartRecords().isEmpty()) result.add(PartMapper.mapPartToResponse(part));});

            return result;
        }

        throw new NotFoundException("404 Not Found");
    }

    @Override
    public String savePart(PartDTO partDTO){
        // Validate parameters
        //Validator.validPartDTO(partDTO);

        Part part = partRepository.findByPartCode(partDTO.getPartCode());

        if (part!= null) {
            Optional<StockCM> stock = stockCMRepository.findByPart_id(part.getId());
            if(!stock.isPresent()) throw new NotFoundException("No stock available for that part id");
            int newQuantity = stock.get().getQuantity() + partDTO.getStock();

            stock.get().setQuantity(newQuantity);
            
            stockCMRepository.save(stock.get());

            return "The stock of the part was updated successfully";
        }
        else {
            Part partInsert = PartMapper.MapPartDTOToPart(partDTO);

            Provider provider = providerRepository.findByName(partDTO.getProviderName());

            partInsert.setProvider_id(provider.getId());

            partInsert = partRepository.save(partInsert);

            StockCM stock = new StockCM(partDTO.getStock(), partInsert.getId(), 1L);

            stockCMRepository.save(stock);

            return "The part was added to the inventory successfully";
        }
    }
}
