package com.gsf.msvc_inventario.repository;

import com.gsf.msvc_inventario.model.entity.Inventario;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventario, Long> {

    @Modifying
    @Query("UPDATE Inventario inv SET inv.cantidadInventario = inv.cantidadInventario + :cantidad WHERE inv.idInventario= :id")
    Inventario addCantidad(@Param("idInventario") Long id, @Param("cantidadInventario") int cantidad);
}
