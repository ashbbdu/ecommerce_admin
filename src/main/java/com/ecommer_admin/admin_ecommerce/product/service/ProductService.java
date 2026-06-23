package com.ecommer_admin.admin_ecommerce.product.service;

import com.ecommer_admin.admin_ecommerce.common.exception.ConflictException;
import com.ecommer_admin.admin_ecommerce.common.exception.ResourceNotFoundException;
import com.ecommer_admin.admin_ecommerce.product.dto.CreateProduct;
import com.ecommer_admin.admin_ecommerce.product.dto.ViewProduct;
import com.ecommer_admin.admin_ecommerce.product.entity.ProductEntity;
import com.ecommer_admin.admin_ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ViewProduct addProduct(CreateProduct createProduct) {
        if(productRepository.existsBySku(createProduct.getSku())) {
            throw new ConflictException("Product with this SKU already present.");
        }
        ProductEntity product = modelMapper.map(createProduct , ProductEntity.class);
        productRepository.save(product);
        return modelMapper.map(product , ViewProduct.class);

    }

    public ViewProduct getProductById (Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new
                ResourceNotFoundException("Product with this id not found !"));

        return modelMapper.map(product , ViewProduct.class);

    }

    public ViewProduct updateProduct(CreateProduct createProduct, Long productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new
                ResourceNotFoundException("Product with this id not found !"));

        if(!createProduct.getSku().equals(product.getSku()) && productRepository.existsBySku(createProduct.getSku())) {
            throw new ConflictException("SKU already present , try different SKU");
        }

        product.setPrice(createProduct.getPrice());
        product.setSku(createProduct.getSku());
        product.setCostPrice(createProduct.getCostPrice());
        product.setStatus(createProduct.getStatus());

        ProductEntity savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct , ViewProduct.class);

    }

    public List<ViewProduct> getAllProducts () {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(res -> modelMapper.map(res , ViewProduct.class)).toList();
    }
}
