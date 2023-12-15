package com.example.batchprocessing;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private String quellbank;
    private String quellkontonummer;
    private String zielbank;
    private String zielkontonummer;
    private LocalDate transaktionsdatum;
    private BigDecimal betrag;

    public String getQuellbank() {
        return quellbank;
    }

    public void setQuellbank(String quellbank) {
        this.quellbank = quellbank;
    }

    public String getQuellkontonummer() {
        return quellkontonummer;
    }

    public void setQuellkontonummer(String quellkontonummer) {
        this.quellkontonummer = quellkontonummer;
    }

    public String getZielbank() {
        return zielbank;
    }

    public void setZielbank(String zielbank) {
        this.zielbank = zielbank;
    }

    public String getZielkontonummer() {
        return zielkontonummer;
    }

    public void setZielkontonummer(String zielkontonummer) {
        this.zielkontonummer = zielkontonummer;
    }

    public LocalDate getTransaktionsdatum() {
        return transaktionsdatum;
    }

    public void setTransaktionsdatum(LocalDate transaktionsdatum) {
        this.transaktionsdatum = transaktionsdatum;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }
}
