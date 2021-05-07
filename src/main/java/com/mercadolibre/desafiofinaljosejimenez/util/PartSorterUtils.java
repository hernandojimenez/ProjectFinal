package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PartSorterUtils {
    //Sorting orders First Requirement
    public static List<Part> getSorter(Map<String, String> params, PartRepository repository, Date date) throws Exception {
        if (params.size()== 0) return repository.findAll();

        String queryType = params.get("queryType");
        String order = params.getOrDefault("order","1");

        switch (queryType) {

            case "C":
                return repository.findAll();

            case "P":
                return getPSorterByOrder(order, repository, date);

            case "V":
                return getVSorterByOrder(order, repository, date );
        }
        return null;
    }

    public static List<Part> getPSorterByOrder(String order, PartRepository repository,Date date) throws Exception {
        if (order.equals("2")) return repository.findByModifiedAttributeDesc(date);
        return repository.findByModifiedAttributeAsc(date);
}

    public static List<Part> getVSorterByOrder(String order, PartRepository repository, Date date) throws Exception {
        if (order.equals("2")) return repository.findByModifiedPriceDesc(date);
        return repository.findByModifiedPriceAsc(date);
    }

}
