package com.ecommer_admin.admin_ecommerce.inventory.service;

import com.ecommer_admin.admin_ecommerce.common.exception.BadRequestException;
import com.ecommer_admin.admin_ecommerce.common.exception.ConflictException;
import com.ecommer_admin.admin_ecommerce.common.exception.ResourceNotFoundException;
import com.ecommer_admin.admin_ecommerce.inventory.dto.CreateInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.UpdateInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.ViewInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.type.InventoryStatus;
import com.ecommer_admin.admin_ecommerce.inventory.entity.InventoryEntity;
import com.ecommer_admin.admin_ecommerce.inventory.repository.InventoryRepository;
import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import com.ecommer_admin.admin_ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ViewInventoryDto createInventory (CreateInventoryDto createInventoryDto , Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found !"));

//        if (product.getInventory() != null) {
//            throw new ConflictException("Inventory already exist for this product !");
//        }

        if (inventoryRepository.existsByProduct(product)) {
            throw new ConflictException("Inventory already exist for this product !");
        }

        if (createInventoryDto.getMaximumStock().compareTo(createInventoryDto.getMinimumStock()) <= 0) {
            throw new BadRequestException("Maximum stock must be greater than minimum stock.");
        }

        if (createInventoryDto.getReorderLevel().compareTo(createInventoryDto.getMaximumStock()) > 0) {
            throw new BadRequestException(
                    "Reorder level cannot exceed maximum stock.");
        }
//        if (createInventoryDto.getReservedStock().compareTo(dto.getAvailableStock()) > 0) {
//            throw new BadRequestException(
//                    "Reserved stock cannot exceed available stock.");
//        }

        InventoryEntity inventory = modelMapper.map(createInventoryDto , InventoryEntity.class);

        if (inventory.getAvailableStock().compareTo(BigDecimal.ZERO) == 0) {
            inventory.setStatus(InventoryStatus.OUT_OF_STOCK);
        } else if (inventory.getAvailableStock().compareTo(inventory.getReorderLevel()) <= 0) {
            inventory.setStatus(InventoryStatus.LOW_STOCK);
        } else {
            inventory.setStatus(InventoryStatus.IN_STOCK);
        }

        inventory.setProduct(product);
        InventoryEntity savedEntity = inventoryRepository.save(inventory);

        return modelMapper.map(savedEntity , ViewInventoryDto.class);
    }


    public ViewInventoryDto updateInventory(UpdateInventoryDto updateInventoryDto , Long inventoryId) {

        InventoryEntity inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));

        if (updateInventoryDto.getMaximumStock().compareTo(updateInventoryDto.getMinimumStock()) <= 0) {
            throw new BadRequestException("Maximum stock must be greater than minimum stock.");
        }

        if (updateInventoryDto.getReorderLevel().compareTo(updateInventoryDto.getMaximumStock()) > 0) {
            throw new BadRequestException("Reorder level cannot exceed maximum stock.");
        }

        inventory.setAvailableStock(updateInventoryDto.getAvailableStock());
        inventory.setMinimumStock(updateInventoryDto.getMinimumStock());
        inventory.setMaximumStock(updateInventoryDto.getMaximumStock());
        inventory.setReorderLevel(updateInventoryDto.getReorderLevel());

        if (inventory.getAvailableStock().compareTo(BigDecimal.ZERO) == 0) {
            inventory.setStatus(InventoryStatus.OUT_OF_STOCK);
        } else if (inventory.getAvailableStock().compareTo(inventory.getReorderLevel()) <= 0) {
            inventory.setStatus(InventoryStatus.LOW_STOCK);
        } else {
            inventory.setStatus(InventoryStatus.IN_STOCK);
        }

        InventoryEntity updatedInventory = inventoryRepository.save(inventory);

        return modelMapper.map(updatedInventory, ViewInventoryDto.class);
    }

    public List<ViewInventoryDto> getInventoriesWithLowStock(InventoryStatus status) {
        List<InventoryEntity> inventories =
                inventoryRepository.findAllByStatus(status);

        return inventories.stream()
                .map(res -> modelMapper.map(res , ViewInventoryDto.class)).toList();
    }
}
