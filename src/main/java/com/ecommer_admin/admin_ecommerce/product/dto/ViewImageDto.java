package com.ecommer_admin.admin_ecommerce.product.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ViewImageDto {
    private Long id;
    private String imageUrl;
    private Integer displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private ProductIdDto product;

//    private List<ViewImageDto> productImages;
//
//    private ProductCategory category;
}
