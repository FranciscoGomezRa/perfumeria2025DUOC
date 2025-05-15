package com.gsf.msvc_inventario.service;

import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.exception.InventoryException;
import com.gsf.msvc_inventario.model.Producto;
import com.gsf.msvc_inventario.model.entity.ProductoSucursal;
import com.gsf.msvc_inventario.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductClientRest productClientRest;

    @Override
    public List<ProductoSucursal> findAll() {
        return this.inventoryRepository.findAll();
    }

    @Override
    public ProductoSucursal findById(Long id) {

        return this.inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );
    }

    @Override
    public ProductoSucursal save(ProductoSucursal productoSucursal) {
        if(inventoryRepository.findById(productoSucursal.getIdProductoSucursal()).isPresent()){
            return this.inventoryRepository.save(productoSucursal);
        }
        return this.inventoryRepository.save(productoSucursal) ;
    }
    @Override
    public ProductoSucursal addCantidadProducto(String nombre, Integer cantidad) {
        ProductoSucursal productoSucursal = this.inventoryRepository.findByNombre(nombre)
                .orElseThrow(() -> new InventoryException("Producto no encontrado"));

        productoSucursal.setCantidadProducto(productoSucursal.getCantidadProducto() + cantidad);
        return inventoryRepository.save(productoSucursal);
    }
}


