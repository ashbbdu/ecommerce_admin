package com.ecommer_admin.admin_ecommerce.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProduct {
    @NotBlank(message = "SKU is required")
    private String sku;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Cost price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal costPrice;

    private Boolean status;

}
