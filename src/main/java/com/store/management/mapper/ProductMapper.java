package com.store.management.mapper;

import com.store.management.dtoRecord.ProductDTO;
import com.store.management.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}

