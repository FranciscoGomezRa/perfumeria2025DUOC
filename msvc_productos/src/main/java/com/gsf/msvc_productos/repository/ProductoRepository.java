package com.gsf.msvc_productos.repository;

import com.gsf.msvc_productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
