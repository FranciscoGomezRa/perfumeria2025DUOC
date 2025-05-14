package com.gsf.msvc_inventario.service;

import com.gsf.msvc_inventario.model.ProductoSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    public InventoryService inventoryService;

    @Override
    public List<ProductoSucursal> findAll() {
        return this.inventoryService.findAll();
    }

    @Override
    public ProductoSucursal findById(Long id) {
        return this.inventoryService.findById(id).orElseThrow;
    }

    @Override
    public ProductoSucursal save(ProductoSucursal productoSucursal) {
        return null;
    }
}
