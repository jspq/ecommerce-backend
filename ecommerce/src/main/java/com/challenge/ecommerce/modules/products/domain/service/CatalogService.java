package com.challenge.ecommerce.modules.products.domain.service;

import com.challenge.ecommerce.modules.products.adapters.input.dto.catalog.CatalogRequestDTO;
import com.challenge.ecommerce.modules.products.adapters.input.dto.catalog.CatalogResponseDTO;
import com.challenge.ecommerce.modules.products.domain.model.Catalog;
import com.challenge.ecommerce.modules.products.domain.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public CatalogResponseDTO createCatalog(CatalogRequestDTO catalogRequestDTO) {
        Catalog catalog = new Catalog();
        catalog.setName(catalogRequestDTO.getName());

        Catalog saveCatalog = catalogRepository.save(catalog);
        return mapToCatalogResponse(saveCatalog);
    }

    public CatalogResponseDTO updateCatalog(Long id, CatalogRequestDTO catalogRequestDTO) {
        Catalog catalogToUpdate = catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalogo no encontrado"));
        catalogToUpdate.setName(catalogRequestDTO.getName());

        Catalog catalogUpdated = catalogRepository.save(catalogToUpdate);
        return mapToCatalogResponse(catalogUpdated);
    }

    public CatalogResponseDTO getCatalogById(Long id) {
        Catalog catalog = catalogRepository.findById(id).orElse(null);
        return mapToCatalogResponse(catalog);
    }

    public List<CatalogResponseDTO> getAllCatalogs() {
        return catalogRepository.findAll().stream()
                .map(this::mapToCatalogResponse)
                .toList();
    }

    public void deleteCatalog(Long id) {
        catalogRepository.deleteById(id);
    }

    private CatalogResponseDTO mapToCatalogResponse(Catalog catalog) {
        CatalogResponseDTO catalogResponseDTO = new CatalogResponseDTO();
        catalogResponseDTO.setId(catalog.getId());
        catalogResponseDTO.setName(catalog.getName());

        return catalogResponseDTO;
    }

}
