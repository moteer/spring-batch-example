package com.example.batchprocessing;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    public CustomLocalDateEditor(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(LocalDate.parse(text, formatter));
    }

    @Override
    public String getAsText() {
        LocalDate value = (LocalDate) getValue();
        return (value != null ? value.format(formatter) : "");
    }

}
