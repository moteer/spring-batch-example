package com.example.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

public class TransactionItemProcessor implements ItemProcessor<Transaction, Account> {



    @Override
    public Account process(Transaction transaction) throws Exception {

        // Hier implementierst du die Logik zur Aktualisierung des Saldos für jedes Konto
        // Das könnte z.B. eine Datenbankabfrage und Berechnungen umfassen

        Account account = new Account();
        account.setBank(transaction.getQuellbank());
        account.setKontonummer(transaction.getQuellkontonummer());
        // Berechne und setze den neuen Saldo basierend auf der Transaktion
        // Beispiel: account.setSaldo(...);
        return account;
    }
}
