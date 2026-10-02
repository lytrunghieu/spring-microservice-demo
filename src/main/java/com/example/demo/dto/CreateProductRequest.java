package com.example.demo.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record  CreateProductRequest(
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 3, max = 150, message = "Tên sản phẩm phải từ 3 đến 150 ký tự")
    String name,

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0.01", message = "Giá sản phẩm phải lớn hơn 0")
    BigDecimal price
){}