package com.store.management.service;
import com.store.management.dtoRecord.ProductDTO;
import com.store.management.entity.Product;
import com.store.management.mapper.ProductMapper;
import com.store.management.repository.CategoryRepository;
import com.store.management.repository.ProductRepository;
import com.store.management.repository.StoreUnitRepository;
import com.store.management.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;
    private final StoreUnitRepository storeUnitRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO) {
        try {
            if (categoryRepository.existsByName(productDTO.categoryName()) &&
                    vendorRepository.existsByName(productDTO.name()) &&
                    storeUnitRepository.existsByName(productDTO.name())) {

                Product product = productMapper.toEntity(productDTO);
                Product savedProduct = productRepository.save(product);
                logger.info("Product {} added successfully.", productDTO.name());
                return productMapper.toDTO(savedProduct);
            } else {
                throw new IllegalArgumentException("Invalid references to Category, Vendor, or Store Unit.");
            }
        } catch (Exception e) {
            logger.error("Error adding product: {}", e.getMessage());
            throw new RuntimeException("Failed to add product", e);
        }
    }

    public ProductDTO findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            logger.info("Product with ID {} found.", id);
            return productMapper.toDTO(product.get());
        } else {
            logger.error("Product with ID {} not found.", id);
            throw new RuntimeException("Product not found");
        }
    }

    @Transactional
    public ProductDTO changeProductPrice(Long productId, Double newPrice) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDTO dto = productMapper.toDTO(product);
        ProductDTO changedProduct = new ProductDTO(dto.id(),dto.categoryName(),newPrice,dto.categoryName());
        Product updatedProduct = productRepository.save(productMapper.toEntity(changedProduct));
        logger.info("Price of product with ID {} changed to {}", productId, newPrice);
        return productMapper.toDTO(updatedProduct);
    }
}
