package com.example.warehouse;

import com.example.warehouse.DealWithFiles.DealWithCSV;
import com.example.warehouse.models.DealModel;
import com.example.warehouse.models.TrackerSuccessModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestDealWithCSV {
    @Autowired
    private DealWithCSV dealWithCSV;

    @Test
    void testParsingValidData() {
        String[] newRow1 = {"1", "MAD", "EUR", "2020-01-01 00:00:00", "70.0"};
        String[] newRow2 = {"2", "USD", "EUR", "2020-01-01 00:00:00", "50.0"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        assertEquals(2, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testWrongId() {
        String[] newRow1 = {"1", "USD", "EUR", "2020-01-01 00:00:00", "70.0"};
        String[] newRow2 = {"2T", "USD", "EUR", "2020-01-01 00:00:00", "50.0"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        assertEquals(1, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testWrongCurrency() {
        String[] newRow1 = {"1", "AEED", "EUR", "2020-01-01 00:00:00", "70.0"};
        String[] newRow2 = {"2", "USD", "EUR", "2020-01-01 00:00:00", "50.0"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        assertEquals(1, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testWrongDate() {
        String[] newRow1 = {"1", "SHP", "EUR", "2020-02-02 0dfd0:00:00", "20.0"};
        String[] newRow2 = {"2", "JOD", "EUR", "2020-01-01 00:00:00", "10.0"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        trackerSuccessModel.getPassedDeals().stream().map(DealModel::getDeal_timestamp).forEach(System.out::println);
        assertEquals(1, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testWrongAmount() {
        String[] newRow1 = {"1", "USD", "EUR", "2020-01-01 00:00:00", "20.0a"};
        String[] newRow2 = {"2", "USD", "EUR", "2020-01-01 00:00:00", "10.0"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        assertEquals(1, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testWrongRow() {
        String[] newRow1 = {"1", "USD", "EUR", "2020-01-01 00:00:00", "20.0"};
        String[] newRow2 = {"2", "USD", "EUR", "2020-01-01 00:00:00"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        assertEquals(1, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testParsingRandomData() {
        String[] newRow1 = {"h2", "gg", "3", "10:10", "555"};
        String[] newRow2 = {"1", "ds", "77", "11:11", "q"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel = dealWithCSV.convertRowsToDeals(result);
        assertEquals(0, trackerSuccessModel.getPassedDeals().size());
    }

    @Test
    void testCurrencyCase() {
        String[] newRow1 = {"1", "SOS", "eur", "2020-01-01 00:00:00", "20.0"};
        String[] newRow2 = {"2", "usd", "eur", "2020-01-01 00:00:00", "10.0"};
        List<String[]> result = List.of(newRow1, newRow2);
        TrackerSuccessModel trackerSuccessModel  = dealWithCSV.convertRowsToDeals(result);
        assertEquals(2, trackerSuccessModel.getPassedDeals().size());
    }
}
