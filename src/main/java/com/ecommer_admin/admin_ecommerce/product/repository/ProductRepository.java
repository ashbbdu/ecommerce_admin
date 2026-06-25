package com.ecommer_admin.admin_ecommerce.product.repository;

import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity , Long> {
    public boolean existsBySku (String sku);

    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.productImages")
    public List<ProductEntity> getAllProducts();
}
