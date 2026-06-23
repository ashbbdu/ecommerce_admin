package com.ecommer_admin.admin_ecommerce.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ViewProduct {
    private Long id;

    private String sku;

    private BigDecimal price;

    private BigDecimal costPrice;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    private Integer quantity;

}
