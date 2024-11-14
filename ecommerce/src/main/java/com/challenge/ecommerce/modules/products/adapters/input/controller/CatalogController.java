package com.challenge.ecommerce.modules.products.adapters.input.controller;

import com.challenge.ecommerce.modules.products.adapters.input.dto.catalog.CatalogRequestDTO;
import com.challenge.ecommerce.modules.products.adapters.input.dto.catalog.CatalogResponseDTO;
import com.challenge.ecommerce.modules.products.domain.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @PostMapping
    public ResponseEntity<CatalogResponseDTO> createCatalog(
            @RequestBody CatalogRequestDTO catalogRequestDTO) {
        CatalogResponseDTO catalogResponseDTO = catalogService.createCatalog(catalogRequestDTO);
        return ResponseEntity.ok(catalogResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogResponseDTO> updateCatalog(@PathVariable Long id,
                                                            @RequestBody CatalogRequestDTO catalogRequestDTO) {
        CatalogResponseDTO catalogResponseDTO = catalogService.updateCatalog(id, catalogRequestDTO);
        return ResponseEntity.ok(catalogResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CatalogResponseDTO>> getAllCatalogs() {
        List<CatalogResponseDTO> allCatalogs = catalogService.getAllCatalogs();
        return ResponseEntity.ok(allCatalogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponseDTO> getCatalog(@PathVariable Long id) {
        CatalogResponseDTO catalogResponseDTO = catalogService.getCatalogById(id);
        return ResponseEntity.ok(catalogResponseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCatalog(@PathVariable Long id) {
        catalogService.deleteCatalog(id);
    }
}
