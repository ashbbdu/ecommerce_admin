package com.ecommer_admin.admin_ecommerce.inventory.dto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.type.InventoryStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ViewInventoryDto {
    private Long id;

    private BigDecimal availableStock;

    private BigDecimal minimumStock;

    private BigDecimal reorderLevel;

    private LocalDateTime lastUpdatedAt;

    private LocalDateTime createdAt;

    private InventoryStatus status;

    private LocalDateTime updatedAt;

    private Long productId;
}
