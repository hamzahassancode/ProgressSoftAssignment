package com.example.warehouse.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "warehouses_deal")
public class DealModel {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "from_currency")
    private CurrencyCode fromCurrency;
    @Enumerated(EnumType.STRING)
    @Column(name = "to_currency")
    private CurrencyCode toCurrency;
    @Column(name = "deal_amount")
    private Double deal_amount;
    @Column(name = "deal_timestamp")
    private Date deal_timestamp;


    public enum CurrencyCode {
        AED, AFN, ALL, AMD, ANG, AOA, ARS, AUD, AWG, AZN, BAM, BBD, BDT, BGN, BHD, BIF, BMD, BND, BOB, BRL,
        BSD, BTN, BWP, BYN, BZD, CAD, CDF, CHF, CLP, CNY, COP, CRC, CUP, CVE, CZK, DJF, DKK, DOP, DZD, EGP,
        ERN, ETB, EUR, FJD, FKP, FOK, GBP, GEL, GGP, GHS, GIP, GMD, GNF, GTQ, GYD, HKD, HNL, HRK, HTG, HUF,
        IDR, ILS, IMP, INR, IQD, IRR, ISK, JEP, JMD, JOD, JPY, KES, KGS, KHR, KID, KMF, KRW, KWD, KYD, KZT,
        LAK, LBP, LKR, LRD, LSL, LYD, MAD, MDL, MGA, MKD, MMK, MNT, MOP, MRU, MUR, MVR, MWK, MXN, MYR, MZN,
        NAD, NGN, NIO, NOK, NPR, NZD, OMR, PAB, PEN, PGK, PHP, PKR, PLN, PYG, QAR, RON, RSD, RUB, RWF, SAR,
        SBD, SCR, SDG, SEK, SGD, SHP, SLL, SOS, SRD, SSP, STN, SYP, SZL, THB, TJS, TMT, TND, TOP, TRY, TTD,
        TVD, TWD, TZS, UAH, UGX, USD, UYU, UZS, VES, VND, VUV, WST, XAF, XCD, XDR, XOF, XPF, YER, ZAR, ZMW, ZWL
    }

}

