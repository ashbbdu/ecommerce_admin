package com.ecommer_admin.admin_ecommerce.inventory.entity;

import com.ecommer_admin.admin_ecommerce.inventory.dto.type.InventoryStatus;
import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Available stock cannot be negative.")
    private BigDecimal availableStock;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum stock cannot be negative.")
    private BigDecimal minimumStock;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Maximum stock cannot be negative.")
    private BigDecimal maximumStock;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Reorder level cannot be negative.")
    private BigDecimal reorderLevel; // will trigger auto purchase if the stock is getting low

    @Column
    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToMany(
            mappedBy = "inventory"
    )
    private List<InventoryTransactionEntity> transactions;

}
