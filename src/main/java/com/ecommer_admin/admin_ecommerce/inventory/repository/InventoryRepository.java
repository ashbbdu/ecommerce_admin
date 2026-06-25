package com.ecommer_admin.admin_ecommerce.inventory.repository;

import com.ecommer_admin.admin_ecommerce.inventory.dto.type.InventoryStatus;
import com.ecommer_admin.admin_ecommerce.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryEntity , Long> {
    public List<InventoryEntity> findAllByStatus(InventoryStatus status);
}
