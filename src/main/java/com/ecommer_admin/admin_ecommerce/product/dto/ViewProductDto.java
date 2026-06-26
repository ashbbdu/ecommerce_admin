package com.ecommer_admin.admin_ecommerce.product.dto;

import com.ecommer_admin.admin_ecommerce.category.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewProductDto {
    private Long id;

    private String sku;

    private BigDecimal price;

    private ProductCategory category;

    private List<ViewImageDto> productImages;
}
