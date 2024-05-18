package com.example.warehouse.Services;

import com.example.warehouse.DealWithFiles.DealWithCSV;
import com.example.warehouse.Repository.DealRepository;
import com.example.warehouse.dealCach.DealCache;
import com.example.warehouse.models.DealModel;
import com.example.warehouse.models.Response;
import com.example.warehouse.models.TrackerSuccessModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@Slf4j
public class DealService {
    private final DealRepository dealRepository;
    private final DealCache dealCache=new DealCache();

    @Autowired
    public DealService(DealRepository dealRepository){
        this.dealRepository=dealRepository;
    }

    public Response insertSingleDeal(DealModel dealModel) {
        if (dealModel == null) {
            log.warn("Invalid deal provided.");
            return Response.builder()
                    .responseType(Response.ResponseType.FAILED)
                    .message("Invalid deal.")
                    .build();
        }

        if (dealRepository.findById(dealModel.getId()).isPresent()) {
            log.warn("Deal with id: {} already exists in repository.", dealModel.getId());
            return Response.builder()
                    .responseType(Response.ResponseType.FAILED)
                    .message("Deal already exists.")
                    .build();
        }
        dealCache.addDealToCache(dealModel);
        dealRepository.save(dealModel);
        log.info("Deal with id: {} added successfully.", dealModel.getId());
        return Response.builder()
                .responseType(Response.ResponseType.SUCCESS)
                .message("Deal added successfully.")
                .build();
    }

    public Response getDealsFromCSVFile(MultipartFile file)  {
        try {
            log.info("Attempting to read CSV file.");
            DealWithCSV dealWithCSV=new DealWithCSV();
            List<String[]> CSVStringFile= dealWithCSV.CSVReader(file);
            TrackerSuccessModel trackerSuccessModel = dealWithCSV.convertRowsToDeals(CSVStringFile);

            Response response = new Response();

            if (trackerSuccessModel.getPassedDeals().isEmpty()) {
                log.warn("No deals found to be added.");
                response.setResponseType(Response.ResponseType.FAILED);
                response.setMessage("No deals found to be added.");
                return response;
            }

            for (DealModel deal : trackerSuccessModel.getPassedDeals()) {
                if (dealRepository.existsById(deal.getId()) || dealCache.containsDeal(deal.getId())) {
                    log.warn("Deal with id: {} already exists.", deal.getId());
                    trackerSuccessModel.insertErrorMessage("Deal with id: " + deal.getId() + " already exists.");
                } else {
                    dealCache.addDealToCache(deal);
                    dealRepository.save(deal);
                    log.info("Deal with id: {} added to repository.", deal.getId());
                }
            }

            return trackerSuccessModel.getErrorMessage().isEmpty() ?
                    Response.builder()
                            .responseType(Response.ResponseType.SUCCESS)
                            .message("All deals added successfully.")
                            .build() :
                    Response.builder()
                            .responseType(Response.ResponseType.PARTIAL_SUCCESS)
                            .message(trackerSuccessModel.getErrorMessage().toString())
                            .build();

        } catch (IOException e) {
            log.error("Error occurred while trying to read the file.", e);
            return Response.builder()
                    .responseType(Response.ResponseType.FAILED)
                    .message("Error occurred while trying to read the file.")
                    .build();
        }
    }
}
