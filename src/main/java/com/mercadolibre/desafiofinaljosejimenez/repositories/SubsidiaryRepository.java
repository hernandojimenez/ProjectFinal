package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubsidiaryRepository extends JpaRepository<Subsidiary,Long> {

    Optional<Subsidiary> findBySubsidiaryNumber(String subsidiaryNumber);
}
