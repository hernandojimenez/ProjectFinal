package com.mercadolibre.desafiofinaljosejimenez.util;

import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.repositories.PartRepository;
import com.mercadolibre.desafiofinaljosejimenez.service.*;

import java.util.Map;

public class SorterUtils {
    public static PartSorter getSorter(Map<String, String> params, PartRepository repository) throws Exception {
        String queryType = params.get("querytype");

        if (params.isEmpty() || queryType.equals("C"))
            return new FindAllPartsSorterImpl(repository);
        else if (queryType.equals("P")) {
            if (params.containsKey("order")) {
                if (params.get("order").equals("1"))
                    return new ModifiedAttributeAscSorterImpl(repository);
                else if (params.get("order").equals("2"))
                    return new ModifiedAttributeDescSorterImpl(repository);
                else
                    throw new Exception("Invalid order");
            }
            else
                return new ModifiedAttributeAscSorterImpl(repository);
        }
        else if (queryType.equals("V")) {
            if (params.containsKey("order")) {
                if (params.containsKey("order") && params.get("order").equals("1"))
                    return new ModifiedPriceAscSorterImpl(repository);
                else if (params.get("order").equals("2"))
                    return new ModifiedPriceDescSorterImpl(repository);
                else
                    throw new Exception("Invalid order");
            }
            else
                throw new Exception("Invalid order");
        }

        throw new Exception();
    }
}
