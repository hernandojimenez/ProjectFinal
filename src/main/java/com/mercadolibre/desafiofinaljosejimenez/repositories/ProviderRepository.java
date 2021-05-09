package com.mercadolibre.desafiofinaljosejimenez.repositories;

import com.mercadolibre.desafiofinaljosejimenez.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByName(String name);
}
