package com.gsf.msvc_inventario.repository;


import com.gsf.msvc_inventario.model.entity.Inventario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findByIdProductoAndIdSucursal(@NotNull(message = "El campo id_producto no puede estar vacio") Long idProducto, @NotNull(message = "El campo id_sucursal no puede estar vacio") Long idSucursal);
    List<Inventario> findByIdSucursal(Long idSucursal);
}
