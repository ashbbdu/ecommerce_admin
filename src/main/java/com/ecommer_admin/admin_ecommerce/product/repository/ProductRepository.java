package com.ecommer_admin.admin_ecommerce.product.repository;

import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity , Long> {
    public boolean existsBySku (String sku);
}
