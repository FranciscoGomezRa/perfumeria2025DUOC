package com.gsf.msvc_inventario.service;

import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.client.SucursalClientRest;
import com.gsf.msvc_inventario.dtos.ProductoDTO;
import com.gsf.msvc_inventario.dtos.ProductoInventarioInfoDTO;
import com.gsf.msvc_inventario.exception.InventoryException;
import com.gsf.msvc_inventario.model.Producto;
import com.gsf.msvc_inventario.model.Sucursal;
import com.gsf.msvc_inventario.model.entity.Inventario;
import com.gsf.msvc_inventario.repository.InventoryRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductClientRest productClientRest;
    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public List<Inventario> findAll() {
        return this.inventoryRepository.findAll();
    }

    @Override
    public ProductoInventarioInfoDTO findById(Long id) {

        Inventario inventario = this.inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );

        ProductoInventarioInfoDTO productoInventarioInfoDTO = new ProductoInventarioInfoDTO();
        productoInventarioInfoDTO.setInventario(inventario);

        ProductoDTO productoDTO = new ProductoDTO();
        Producto producto = this.productClientRest.findById(inventario.getIdProducto()).getBody();

        productoDTO.setNombreProducto(producto.getNombreProducto());
        productoInventarioInfoDTO.setInventario(inventario);
        return productoInventarioInfoDTO;
    }

    @Override
    public Inventario save(Inventario inventario) {
        try{
            Producto producto = this.productClientRest.findById(inventario.getIdProducto()).getBody();
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal()).getBody();
            if(this.inventoryRepository.existsById(inventario.getIdProducto())){
                throw new InventoryException("El producto ya existe en la sucursal");
            }
            return this.inventoryRepository.save(inventario);
        }catch(FeignException ex){
            throw new InventoryException(ex.getMessage());
        }
    }

    @Override
    public Inventario addCantidad(Long idInventario, Integer cantidadInventario) {

        Inventario inventario = this.inventoryRepository.findById(idInventario).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );
        return this.inventoryRepository.addCantidad(idInventario, cantidadInventario);
    }

    @Override
    public List<Inventario> findByIdSucursal(Long id) {
        return List.of();
    }


}


