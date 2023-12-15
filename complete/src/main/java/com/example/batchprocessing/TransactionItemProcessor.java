package com.example.batchprocessing;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class TransactionItemProcessor implements ItemProcessor<Transaction, Account> {

    @Autowired
    public AccountRepository accountRepository;

    @Override
    public Account process(Transaction transaction) throws Exception {

        List<Account> accounts = accountRepository.findByKontoNummer(transaction.getZielkontonummer());

        System.out.println(accounts);
        Account account = accounts.get(0);

        BigDecimal currentSaldo = account.getSaldo();
        BigDecimal transactionBetrag = transaction.getBetrag();
        account.setSaldo(currentSaldo.add(transactionBetrag));
        System.out.printf("Transaction: Account %s(%s) von Saldo alt: %s auf Saldo neu:%s",
                account.getBank(), account.getKontoNummer(), currentSaldo, account.getSaldo());

        accountRepository.save(account);

        return account;
    }
}
