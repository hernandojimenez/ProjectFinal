package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.Part;
import com.mercadolibre.desafiofinaljosejimenez.model.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    @Query("select p from Part p " +
            "join PartRecord pr on p.id = pr.part.id " +
            "where pr.lastModification >= :date " +
            "and pr.lastModification <= current_date " +
            "and pr.modifiedField <> 'normal_price'" +
            "and pr.modifiedField <> 'sale_price'" +
            "and pr.modifiedField <> 'urgent_price' " +
            "order by pr.lastModification asc")
    List<Part> findByModifiedAttributeAsc(@Param("date") Date date);

    @Query("select p from Part p " +
            "join PartRecord pr on p.id = pr.part.id " +
            "where pr.lastModification >= :date " +
            "and pr.lastModification <= current_date " +
            "and pr.modifiedField <> 'normal_price'" +
            "and pr.modifiedField <> 'sale_price'" +
            "and pr.modifiedField <> 'urgent_price' " +
            "order by pr.lastModification desc")
    List<Part> findByModifiedAttributeDesc(@Param("date") Date date);

    @Query("select p from Part p " +
            "join PartRecord pr on p.id = pr.part.id " +
            "where pr.lastModification >= :date " +
            "and pr.lastModification <= current_date " +
            "and pr.modifiedField = 'normal_price' " +
            "or pr.modifiedField = 'sale_price' " +
            "or pr.modifiedField = 'urgent_price' " +
            "order by pr.lastModification asc")
    List<Part> findByModifiedPriceAsc(@Param("date") Date date);

    @Query("select p from Part p " +
            "join PartRecord pr on p.id = pr.part.id " +
            "where pr.lastModification >= :date " +
            "and pr.lastModification <= current_date " +
            "and pr.modifiedField = 'normal_price' " +
            "or pr.modifiedField = 'sale_price' " +
            "or pr.modifiedField = 'urgent_price' " +
            "order by pr.lastModification desc")
    List<Part> findByModifiedPriceDesc(@Param("date") Date date);
}
