package com.ecommer_admin.admin_ecommerce.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateInventoryDto {

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Available stock cannot be negative.")
    private BigDecimal availableStock;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Minimum stock cannot be negative.")
    private BigDecimal minimumStock;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Maximum stock cannot be negative.")
    private BigDecimal maximumStock;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Reorder level cannot be negative.")
    private BigDecimal reorderLevel;
}