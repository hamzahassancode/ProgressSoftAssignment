package com.example.warehouse.DealWithFiles;

import com.example.warehouse.Validaiton.DealValidation;
import com.example.warehouse.models.DealModel;
import com.example.warehouse.models.TrackerSuccessModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
@Service
public class DealWithCSV {
private final DealValidation dealValidation=new DealValidation();

public List<String[]> CSVReader(MultipartFile file) throws IOException {
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {
            return csvReader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException();
        }
    }
    private DealModel createDealFromCSV(String[] csvRow) throws Exception {
        try {

            return new DealModel(
                    dealValidation.validId(csvRow[0]),
                    dealValidation.validCurrencyCode(csvRow[1]),
                    dealValidation.validCurrencyCode(csvRow[2]),
                    dealValidation.validAmount(csvRow[4]),
                    dealValidation.validDate(csvRow[3])
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            dealValidation.logAndThrowException(csvRow, "some of fields missing.");
        } catch (Exception e) {
            dealValidation.logAndThrowException(csvRow, e.getMessage());
        }
        return null; // This will never be reached due to exceptions thrown above
    }
    public TrackerSuccessModel convertRowsToDeals(List<String[]> csvRows) {
        TrackerSuccessModel trackerModel=new TrackerSuccessModel();
        ArrayList<DealModel> deals = new ArrayList<>();

        for (String[] csvRow : csvRows) {
            if (dealValidation.isHeaderRow(csvRow)) {
                continue;
            }
            try {
                DealModel deal = createDealFromCSV(csvRow);
                deals.add(deal);
            } catch (Exception e) {
                trackerModel.insertErrorMessage(e.getMessage());
            }
        }

        trackerModel.setPassedDeals(deals);
        return trackerModel;
    }

}
