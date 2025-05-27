package com.gsf.msvc_detallepedido.clients;

import com.gsf.msvc_detallepedido.dtos.BuscaStockPorIdDTO;
import com.gsf.msvc_detallepedido.dtos.BuscadorPorIDSucursalDTO;
import com.gsf.msvc_detallepedido.model.Inventario;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(name="msvc-inventario",url="localhost:8081/api/v1/inventario")
public interface InventarioClientRest {

    @GetMapping
    ResponseEntity<List<Inventario>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<Inventario> findById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<Inventario> save(@RequestBody @Valid Inventario inventario);

    @PostMapping("/productos")
    ResponseEntity<List<Inventario>> save(@RequestBody @Valid BuscadorPorIDSucursalDTO dto);

    @PostMapping("/stock")
    ResponseEntity<Boolean> stockInventario(@RequestBody@Valid BuscaStockPorIdDTO stockSolicitado);


}
