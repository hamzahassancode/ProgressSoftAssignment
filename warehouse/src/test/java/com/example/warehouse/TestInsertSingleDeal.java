package com.example.warehouse;

import com.example.warehouse.Repository.DealRepository;
import com.example.warehouse.Services.DealService;
import com.example.warehouse.models.DealModel;

import com.example.warehouse.models.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestInsertSingleDeal {
DealModel dealModel2=new DealModel();
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealService dealService;

    @Test
    public void testInsertSingleDeal_Success() {
        DealModel dealModel = DealModel.builder()
                .id(1L)
                .fromCurrency(DealModel.CurrencyCode.USD)
                .toCurrency(DealModel.CurrencyCode.EUR)
                .deal_amount(1000.0)
                .deal_timestamp(new Date())
                .build();

        when(dealRepository.findById(1L)).thenReturn(Optional.empty());
        when(dealRepository.save(dealModel)).thenReturn(dealModel);

        Response response = dealService.insertSingleDeal(dealModel);

        assertEquals(Response.ResponseType.SUCCESS, response.getResponseType());
        assertEquals("Deal added successfully.", response.getMessage());
        verify(dealRepository, times(1)).findById(1L);
        verify(dealRepository, times(1)).save(dealModel);
    }

    @Test
    public void testInsertSingleDeal_AlreadyExists() {
        DealModel dealModel = DealModel.builder()
                .id(1L)
                .fromCurrency(DealModel.CurrencyCode.USD)
                .toCurrency(DealModel.CurrencyCode.EUR)
                .deal_amount(1000.0)
                .deal_timestamp(new Date())
                .build();

        when(dealRepository.findById(1L)).thenReturn(Optional.of(dealModel));

        Response response = dealService.insertSingleDeal(dealModel);

        assertEquals(Response.ResponseType.FAILED, response.getResponseType());
        assertEquals("Deal already exists.", response.getMessage());
        verify(dealRepository, times(1)).findById(1L);
        verify(dealRepository, never()).save(any());
    }

    @Test
    public void testInsertSingleDeal_InvalidDeal() {
        Response response = dealService.insertSingleDeal(null);

        assertEquals(Response.ResponseType.FAILED, response.getResponseType());
        assertEquals("Invalid deal.", response.getMessage());
        verify(dealRepository, never()).findById(any());
        verify(dealRepository, never()).save(any());
    }
}
