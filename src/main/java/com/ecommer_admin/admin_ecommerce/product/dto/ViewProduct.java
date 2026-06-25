package com.ecommer_admin.admin_ecommerce.product.dto;

import com.ecommer_admin.admin_ecommerce.product.entity.ProductImageEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ViewProduct {
    private Long id;

    private String sku;

    private BigDecimal price;

    private BigDecimal costPrice;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<ViewImageDto> productImages;

//    private Integer quantity;

}
