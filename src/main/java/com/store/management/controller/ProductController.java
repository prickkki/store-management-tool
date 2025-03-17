package com.store.management.controller;

import com.store.management.dtoRecord.ProductDTO;
import com.store.management.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO savedProduct = productService.addProduct(productDTO);
            return ResponseEntity.status(201).body(savedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{productId}/price")
    public ResponseEntity<ProductDTO> changeProductPrice(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productService.changeProductPrice(productId, productDTO.sellingPrice());
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        try {
            ProductDTO productDTO = productService.findProductById(productId);
            return ResponseEntity.ok(productDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


