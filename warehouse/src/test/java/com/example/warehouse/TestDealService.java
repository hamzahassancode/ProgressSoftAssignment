package com.example.warehouse;

import com.example.warehouse.Repository.DealRepository;
import com.example.warehouse.Services.DealService;
import com.example.warehouse.models.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestDealService {

    @InjectMocks
    private DealService dealService;

    @Mock
    private DealRepository dealRepository;

    @Test
    void testSuccessCase() throws IOException {
        MultipartFile file = createMockMultipartFile(
                "ID,from Currency ,to Currency ,deal Timestamp,deal Amount\n" +
                        "1,EUR,USD,2024-05-17 10:30:00,500\n" +
                        "2,GBP,USD,2024-05-17 10:30:00,1500\n" +
                        "3,JPY,USD,2024-05-17 10:30:00,300\n" +
                        "4,CHF,USD,2024-05-17 10:30:00,400\n" +
                        "5,CAD,USD,2024-05-17 10:30:00,600\n" +
                        "6,AUD,USD,2024-05-17 10:30:00,700\n"
        );

        Response response = dealService.getDealsFromCSVFile(file);

        assertEquals("SUCCESS", response.getResponseType().toString());
    }

    @Test
    void testFailureCase() throws IOException {
        MultipartFile file = createMockMultipartFile(
                "ID,from Currency ,to Currency ,deal Timestamp,deal Amount\n" +
                        "1,EUR,U2SD,2024-05-17 10:30:00,5M0\n"
        );

        Response response = dealService.getDealsFromCSVFile(file);

        assertEquals("FAILED", response.getResponseType().toString());
    }

    @Test
    void testPartialCase() throws IOException {
        MultipartFile file = createMockMultipartFile(
                "ID,from Currency ,to Currency ,deal Timestamp,deal Amount\n" +
                        "1,EUR,USD,2024-05-17 10:30:00,500\n" +
                        "2,GBP,USD,2024-05-17 10:30:00,1500\n" +
                        "3,JPY,USD,2024-05-17 10:30:00,300\n" +
                        "4,CHF,USD,2024-05-17 10:30:00,400\n" +
                        "5,CAD,USD,2024-05-17 10:30:00,600\n" +
                        "6,AUD,USD,2024-05-17 10:30:00,700\n" +
                        "7,NZD,USD,2024-05-17 10:30:00,800\n" +
                        "8,SEK,USD,2024-05-17 10:30:00,kkkk00\n" +
                        "9,JAVA,USD,2024-05-17 10:30:00,1000\n" +
                        "10,DKK,USD,2024-05-17 10:30:00,1100\n" +
                        "11,ZAR,USD,2024-05-17 10:30:00,1200\n" +
                        "12,HKD,USD,2024-05-17 10:30:00,1300\n" +
                        "13,SGD,USD,2024-05-17 10:30:00,140jjjj0\n" +
                        "14,THB,USD,2024-05-17 10:30:00,1500\n" +
                        "15,MYR,USD,2024-05-17 10:30:00,1600\n" +
                        "16,IDR,USD,2024-05-17 10:30:00,500\n"
        );

        Response response = dealService.getDealsFromCSVFile(file);

        assertEquals("PARTIAL_SUCCESS", response.getResponseType().toString());
    }

    @Test
    void testDuplicateIds() throws IOException {
        MultipartFile file = createMockMultipartFile(
                "ID,from Currency ,to Currency ,deal Timestamp,deal Amount\n" +
                        "1,EUR,USD,2024-05-17 10:30:00,500\n" +
                        "2,GBP,USD,2024-05-17 10:30:00,1500\n" +
                        "3,JPY,USD,2024-05-17 10:30:00,300\n" +
                        "4,CHF,USD,2024-05-17 10:30:00,400\n" +
                        "5,CAD,USD,2024-05-17 10:30:00,600\n" +
                        "6,AUD,USD,2024-05-17 10:30:00,700\n" +
                        "7,NZD,USD,2024-05-17 10:30:00,800\n" +
                        "7,SEK,USD,2024-05-17 10:30:00,900\n" +// duplicate
                        "9,NOK,USD,2024-05-17 10:30:00,1000\n" +
                        "10,DKK,USD,2024-05-17 10:30:00,1100\n" +
                        "11,ZAR,USD,2024-05-17 10:30:00,1200\n" +
                        "12,HKD,USD,2024-05-17 10:30:00,1300\n" +
                        "13,SGD,USD,2024-05-17 10:30:00,1400\n" +
                        "14,THB,USD,2024-05-17 10:30:00,1500\n" +
                        "15,MYR,USD,2024-05-17 10:30:00,1600\n" +
                        "15,IDR,USD,2024-05-17 10:30:00,1700\n" +// duplicate
                        "16,PHP,USD,2024-05-17 10:30:00,1800\n" +
                        "17,VND,USD,2024-05-17 10:30:00,1900\n"
        );

        Response response = dealService.getDealsFromCSVFile(file);

        assertEquals("Deal with id: 7 already exists.\nDeal with id: 15 already exists.\n", response.getMessage());
    }

    private MultipartFile createMockMultipartFile(String content) {
        return new MockMultipartFile("file", "test.csv", "text/plain", content.getBytes());
    }
}
