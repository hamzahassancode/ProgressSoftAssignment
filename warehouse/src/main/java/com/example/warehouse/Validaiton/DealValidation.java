package com.example.warehouse.Validaiton;

import com.example.warehouse.models.DealModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
@Service
@Slf4j
public class DealValidation {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String HEADER_IDENTIFIER = "ID";
    private static final String ERROR_TEMPLATE = "Error processing Deal: %s, reason: %s";
    public boolean isHeaderRow(String[] csvRow) {
        return HEADER_IDENTIFIER.equalsIgnoreCase(csvRow[0]);
    }

    public void logAndThrowException(String[] csvRow, String reason) throws Exception {
        String errorMessage = String.format(ERROR_TEMPLATE, Arrays.toString(csvRow), reason);
        log.warn(errorMessage);
        throw new Exception(errorMessage);
    }

    public long validId(String id) throws IOException {
        if (id.isEmpty()) {
            throw new IOException("ID is empty.");
        }
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing ID, please check the format.", e);
        }
    }

    public DealModel.CurrencyCode validCurrencyCode(String currency) throws IOException {
        if (currency.isEmpty()) {
            throw new IOException("Currency is empty.");
        }
        try {
            return DealModel.CurrencyCode.valueOf(currency.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IOException("Error parsing currency.", e);
        }
    }

    public Date validDate(String date) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IOException("Error parsing date, please check the format.", e);
        }
    }

    public double validAmount(String amount) throws IOException {
        if (amount.isEmpty()) {
            throw new IOException("Amount is empty.");
        }
        try {
            return Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new IOException("Error parsing amount, please check the format.", e);
        }
    }

}
