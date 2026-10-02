package com.example.demo.dto;

import java.math.BigDecimal;

public record ExchangeRateResponse(
    String currency,
    BigDecimal rate
){}