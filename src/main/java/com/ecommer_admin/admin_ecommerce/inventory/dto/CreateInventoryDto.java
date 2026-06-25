package com.ecommer_admin.admin_ecommerce.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class CreateInventoryDto {

    @NotNull(message = "Available stock is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Available stock should be at least 0")
    private BigDecimal availableStock;

    @NotNull(message = "Minimum stock is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum stock should be at least 0")
    private BigDecimal minimumStock;

    @NotNull(message = "Minimum stock is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum stock should be at least 0")
    private BigDecimal maximumStock;

    @NotNull(message = "Reorder level is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Reorder level should be at least 0")
    private BigDecimal reorderLevel;
}
