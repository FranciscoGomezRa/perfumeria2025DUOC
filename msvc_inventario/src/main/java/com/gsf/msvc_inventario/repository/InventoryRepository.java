package com.gsf.msvc_inventario.repository;

import com.gsf.msvc_inventario.model.ProductoSucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<ProductoSucursal , Long> {
}
