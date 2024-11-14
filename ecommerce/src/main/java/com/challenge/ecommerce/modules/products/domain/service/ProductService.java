package com.challenge.ecommerce.modules.products.domain.service;

import com.challenge.ecommerce.modules.products.adapters.input.dto.product.ProductRequestDTO;
import com.challenge.ecommerce.modules.products.adapters.input.dto.product.ProductResponseDTO;
import com.challenge.ecommerce.modules.products.domain.model.Catalog;
import com.challenge.ecommerce.modules.products.domain.model.Product;
import com.challenge.ecommerce.modules.products.domain.repository.CatalogRepository;
import com.challenge.ecommerce.modules.products.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Catalog catalog = catalogRepository.findById(productRequestDTO.getCatalogId())
                .orElseThrow(() -> new RuntimeException("No ha encontrado el catalogo"));
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setCatalog(catalog);
        product.setIsActive(productRequestDTO.getIsActive());

        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el producto"));
        Catalog catalog = catalogRepository.findById(productRequestDTO.getCatalogId())
                .orElseThrow(() -> new RuntimeException("No ha encontrado el catalogo"));
        productToUpdate.setName(productRequestDTO.getName());
        productToUpdate.setDescription(productRequestDTO.getDescription());
        productToUpdate.setPrice(productRequestDTO.getPrice());
        productToUpdate.setCatalog(catalog);
        productToUpdate.setIsActive(productRequestDTO.getIsActive());

        Product productUpdated = productRepository.save(productToUpdate);
        return mapToProductResponse(productUpdated);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el producto"));
        return mapToProductResponse(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductResponseDTO mapToProductResponse(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setCatalogId(product.getCatalog().getId());
        return productResponseDTO;
    }

}
