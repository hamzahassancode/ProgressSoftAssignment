package com.example.warehouse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackerSuccessModel {

    private ArrayList<DealModel> passedDeals = new ArrayList<>();
    private final StringBuilder errorMessage = new StringBuilder();
    public void insertErrorMessage(String message){

        this.errorMessage.append(message + "\n");
    }

}
