package com.gsf.msvc_inventario.service;

import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.client.SucursalClientRest;
import com.gsf.msvc_inventario.dtos.BuscaStockPorIdDTO;
import com.gsf.msvc_inventario.dtos.InventoryUpdateDTO;
import com.gsf.msvc_inventario.exception.InventoryException;
import com.gsf.msvc_inventario.model.Producto;
import com.gsf.msvc_inventario.model.Sucursal;
import com.gsf.msvc_inventario.model.entity.Inventario;
import com.gsf.msvc_inventario.repository.InventoryRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
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
    public Inventario findById(Long id) {

        return this.inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );
    }

    @Override
    public Inventario save(Inventario inventario) {
        try{

            Producto producto = this.productClientRest.findById(inventario.getIdProducto()).getBody();
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal()).getBody();
            if(this.inventoryRepository.existsById(inventario.getIdProducto())){
                throw new InventoryException("El producto ya existe en la sucursal");
            }else if (producto==null){
                throw new InventoryException("El producto no existe en la sucursal");
            }else if (sucursal==null){
                throw new InventoryException("La sucursal no existe");
            }
            return this.inventoryRepository.save(inventario);
        }catch(FeignException ex){
            throw new InventoryException(ex.getMessage());
        }
    }


    @Override
    @Transactional
    public Boolean stockInventario(BuscaStockPorIdDTO stockSolicitado) {


        Inventario inventario = this.inventoryRepository.findByIdProductoAndIdSucursal(stockSolicitado.getIdProducto(), stockSolicitado.getIdSucursal()).orElseThrow(
                () -> new InventoryException("El producto no existe en la sucursal")
        );

        if(inventario.getCantidadInventario()-stockSolicitado.getCantidadSolicitada() < 0){
            throw new InventoryException("Stock Insuficiente");
        }

        inventario.setCantidadInventario(inventario.getCantidadInventario()-stockSolicitado.getCantidadSolicitada());
        return true;
    }


    @Override
    public List<Inventario> findByIdSucursal(Long id) {

        return this.inventoryRepository.findByIdSucursal(id);
    }


    @Override
    @Transactional
    public void deleteById(Long id){
        if(this.inventoryRepository.findById(id).isPresent()){
            this.inventoryRepository.deleteById(id);
        }else{
            throw new InventoryException("No se muestra el inventario con:" +id);
        }
    }

    @Override
    public Inventario update(Long idInventario, InventoryUpdateDTO inventoryUpdateDTO) {
        Inventario inventario = inventoryRepository.findById(idInventario).orElseThrow(
                () -> new InventoryException("El inventario con id" + idInventario + "no existe")
        );
        if(inventoryUpdateDTO.getCantidadInventario() != null){
            inventario.setCantidadInventario(inventoryUpdateDTO.getCantidadInventario());
        }
        return this.inventoryRepository.save(inventario);
    }
}


