package com.ecommer_admin.admin_ecommerce.inventory.entity;

import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import jakarta.persistence.*;
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

    @Column
    @Min(value = 0 , message = "Available stocks should be greater than 0")
    private BigDecimal availableStock;

    @Column
    @Min(value =  0, message = "Minimum stocks should be greater than 0")
    private BigDecimal minimumStock;


    @Column
    @Min(value = 0 , message = "Minimum stocks should be greater than 0")
    private BigDecimal maximumStock;

    @Column
    @Min(value = 0 , message = "Reorder stocks Level should be greater than 0")
    private BigDecimal reorderLevel; // will trigger auto purchase if the stock is getting low

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
