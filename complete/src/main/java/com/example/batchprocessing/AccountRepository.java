package com.example.batchprocessing;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByKontoNummer(String zielkontonummer);
}
