package com.ecommer_admin.admin_ecommerce.inventory.service;

import com.ecommer_admin.admin_ecommerce.common.exception.BadRequestException;
import com.ecommer_admin.admin_ecommerce.common.exception.ConflictException;
import com.ecommer_admin.admin_ecommerce.common.exception.ResourceNotFoundException;
import com.ecommer_admin.admin_ecommerce.inventory.dto.CreateInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.ViewInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.entity.InventoryEntity;
import com.ecommer_admin.admin_ecommerce.inventory.repository.InventoryRepository;
import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import com.ecommer_admin.admin_ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ViewInventoryDto createInventory (CreateInventoryDto createInventoryDto , Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found !"));

        if (product.getInventory() != null) {
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

        inventory.setProduct(product);
        InventoryEntity savedEntity = inventoryRepository.save(inventory);

        return modelMapper.map(savedEntity , ViewInventoryDto.class);
    }
}
