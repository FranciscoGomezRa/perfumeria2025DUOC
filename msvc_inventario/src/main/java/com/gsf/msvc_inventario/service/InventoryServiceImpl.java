package com.gsf.msvc_inventario.service;

import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.dtos.ProductoInventarioInfoDTO;
import com.gsf.msvc_inventario.exception.InventoryException;
import com.gsf.msvc_inventario.model.entity.ProductoInventario;
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
    public List<ProductoInventario> findAll() {
        return this.inventoryRepository.findAll();
    }

    @Override
    public ProductoInventario findById(Long id) {

        return this.inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );
    }

    @Override
    public ProductoInventario save(ProductoInventario productoInventario) {
        if(inventoryRepository.findById(productoInventario.getIdProductoSucursal()).isPresent()){
            return this.inventoryRepository.save(productoInventario);
        }
        return this.inventoryRepository.save(productoInventario) ;
    }
    @Override
    public ProductoInventario addCantidadProducto(String nombre, Integer cantidad) {
        ProductoInventario productoInventario = this.inventoryRepository.findByNombre(nombre)
                .orElseThrow(() -> new InventoryException("Producto no encontrado"));

        productoInventario.setCantidadProducto(productoInventario.getCantidadProducto() + cantidad);
        return inventoryRepository.save(productoInventario);
    }

    public ProductoInventarioInfoDTO save(ProductoInventarioInfoDTO productoInventarioinfoDTO){


        return null;
    }

}


