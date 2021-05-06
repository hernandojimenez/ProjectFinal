package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.service.*;

import java.util.Map;

public class SorterUtils {

    public static PartSorter getSorter(Map<String, String> params, PartRepository repository) throws Exception {
        if (params.size()== 0) return new FindAllPartsSorterImpl(repository);

        String queryType = params.get("queryType");
        String order = params.getOrDefault("order","1");

        switch (queryType) {

            case "C":
                return new FindAllPartsSorterImpl(repository);

            case "P":
                return getPSorterByOrder(order, repository);

            case "V":
                return getVSorterByOrder(order, repository);
        }
        return null;
    }

    public static PartSorter getPSorterByOrder(String order, PartRepository repository) throws Exception {
        if (order.equals("2")) return new ModifiedAttributeDescSorterImpl(repository);
        return new ModifiedAttributeAscSorterImpl(repository);
}

    public static PartSorter getVSorterByOrder(String order, PartRepository repository) throws Exception {
        if (order.equals("2")) return new ModifiedPriceDescSorterImpl(repository);
        return new ModifiedPriceAscSorterImpl(repository);
    }

}
