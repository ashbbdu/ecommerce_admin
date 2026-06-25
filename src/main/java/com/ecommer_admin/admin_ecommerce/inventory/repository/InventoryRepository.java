package com.ecommer_admin.admin_ecommerce.inventory.repository;

import com.ecommer_admin.admin_ecommerce.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity , Long> {
}
