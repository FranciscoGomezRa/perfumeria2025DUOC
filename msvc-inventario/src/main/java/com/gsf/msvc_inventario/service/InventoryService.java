package com.gsf.msvc_inventario.service;


import com.gsf.msvc_inventario.dtos.BuscaStockPorIdDTO;
import com.gsf.msvc_inventario.dtos.InventoryUpdateDTO;
import com.gsf.msvc_inventario.model.entity.Inventario;

import java.util.List;

public interface InventoryService {
    List<Inventario> findAll();
    Inventario findById(Long id);
    Inventario save(Inventario inventario);
    Boolean stockInventario(BuscaStockPorIdDTO stockSolicitado );
    List<Inventario> findByIdSucursal(Long id);
    void deleteById(Long id);
    Inventario update(Long id, InventoryUpdateDTO inventoryUpdateDTO );

}
