package com.ecommer_admin.admin_ecommerce.inventory.controller;

import com.ecommer_admin.admin_ecommerce.inventory.dto.CreateInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.UpdateInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.ViewInventoryDto;
import com.ecommer_admin.admin_ecommerce.inventory.dto.type.InventoryStatus;
import com.ecommer_admin.admin_ecommerce.inventory.service.InventoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inventory")
@RequiredArgsConstructor
@Validated
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping(path = "/create/{productId}")
    public ViewInventoryDto createInventory (@RequestBody @Validated CreateInventoryDto createInventoryDto , @PathVariable Long productId) {
        return inventoryService.createInventory(createInventoryDto , productId);
    }

    @PutMapping(path = "/update/{}")
    public ViewInventoryDto updateInventory (@RequestBody @Validated UpdateInventoryDto updateInventoryDto , @PathVariable Long productId) {
        return inventoryService.updateInventory(updateInventoryDto , productId);
    }

    @GetMapping(path = "/low-stock")
    public List<ViewInventoryDto> getInventoriesWithLowStock () {
        return inventoryService.getInventoriesWithLowStock(InventoryStatus.LOW_STOCK);
    }

}
