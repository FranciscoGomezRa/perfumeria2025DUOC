package com.gsf.msvc_inventario.service;

import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.dtos.ProductoDTO;
import com.gsf.msvc_inventario.dtos.ProductoInventarioInfoDTO;
import com.gsf.msvc_inventario.exception.InventoryException;
import com.gsf.msvc_inventario.model.Producto;
import com.gsf.msvc_inventario.model.entity.ProductoInventario;
import com.gsf.msvc_inventario.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    public ProductoInventarioInfoDTO findById(Long id) {

        ProductoInventario productoInventario = this.inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );

        ProductoInventarioInfoDTO productoInventarioInfoDTO = new ProductoInventarioInfoDTO();
        productoInventarioInfoDTO.setProductoInventario(productoInventario);

        ProductoDTO productoDTO = new ProductoDTO();
        Producto producto = this.productClientRest.findById(productoInventario.getIdProducto()).getBody();

        productoDTO.setIdProducto();
        return productoInventarioInfoDTO;
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


}


