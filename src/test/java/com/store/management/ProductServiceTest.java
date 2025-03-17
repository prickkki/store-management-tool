package com.store.management;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.store.management.dtoRecord.ProductDTO;
import com.store.management.entity.Product;
import com.store.management.mapper.ProductMapper;
import com.store.management.repository.CategoryRepository;
import com.store.management.repository.ProductRepository;
import com.store.management.repository.StoreUnitRepository;
import com.store.management.repository.VendorRepository;
import com.store.management.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private StoreUnitRepository storeUnitRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDTO = new ProductDTO(1L, "Product Name", 100.0, "Electronics");
    }

    @Test
    void addProduct_ShouldAddProductSuccessfully() {
        when(categoryRepository.existsByName(any())).thenReturn(true);
        when(vendorRepository.existsByName(any())).thenReturn(true);
        when(storeUnitRepository.existsByName(any())).thenReturn(true);
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productService.addProduct(productDTO);

        assertNotNull(result);
        assertEquals(productDTO.id(), result.id());
        assertEquals(productDTO.name(), result.name());
        assertEquals(productDTO.sellingPrice(), result.sellingPrice());
        assertEquals(productDTO.categoryName(), result.categoryName());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void addProduct_ShouldThrowException_WhenCategoryNotExists() {
        when(categoryRepository.existsByName(any())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(productDTO));
    }

    @Test
    void changeProductPrice_ShouldChangePriceSuccessfully() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        ProductDTO updatedProductDTO = new ProductDTO(productDTO.id(), productDTO.name(), 150.0, productDTO.categoryName());

        ProductDTO result = productService.changeProductPrice(productDTO.id(), updatedProductDTO.sellingPrice());

        assertNotNull(result);
        assertEquals(150.0, result.sellingPrice());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void changeProductPrice_ShouldThrowException_WhenProductNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.changeProductPrice(productDTO.id(), productDTO.sellingPrice()));
    }
}
