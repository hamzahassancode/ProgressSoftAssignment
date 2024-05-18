package com.example.warehouse.controller;

import com.example.warehouse.Services.DealService;
import com.example.warehouse.models.DealModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DealController {
    private final DealService dealService;
    public DealController(DealService dealService){
        this.dealService=dealService;
    }


    @PostMapping("/insertSingleDeal")
    public String insertSingleDeal(@RequestParam("id") Long id,
                                @RequestParam("fromCurrency") DealModel.CurrencyCode fromCurrency,
                                @RequestParam("toCurrency") DealModel.CurrencyCode toCurrency,
                                @RequestParam("amount") Double amount,
                                @RequestParam("timestamp") String timestamp,
                                Model model) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedTimestamp = formatter.parse(timestamp);

            DealModel deal = DealModel.builder()
                    .id(id)
                    .fromCurrency(fromCurrency)
                    .toCurrency(toCurrency)
                    .deal_amount(amount)
                    .deal_timestamp(parsedTimestamp)
                    .build();

            model.addAttribute("responseInsertSingleDeal", dealService.insertSingleDeal(deal));
        } catch (ParseException e) {
            model.addAttribute("responseInsertSingleDeal", "Failed to parse date: " + e.getMessage());
        }

        return "index";
    }

    @PostMapping("/getDealsFromCSVFile")
    public String getDealsFromCSVFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        model.addAttribute("response", dealService.getDealsFromCSVFile(file));
        return "index";
    }
}
