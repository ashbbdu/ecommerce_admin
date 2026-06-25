package com.ecommer_admin.admin_ecommerce.inventory.dto;


import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateInventoryDto {
    @DecimalMin(value = "10.0", inclusive = true, message = "Available stock should be at least 10")
    private BigDecimal availableStock;

    @DecimalMin(value = "10.0", inclusive = true, message = "Minimum stock should be at least 10")
    private BigDecimal minimumStock;

    @DecimalMin(value = "10.0", inclusive = true, message = "Reorder level should be at least 10")
    private BigDecimal reorderLevel;
}
