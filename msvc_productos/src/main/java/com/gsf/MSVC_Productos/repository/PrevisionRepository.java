package com.gsf.MSVC_Productos.repository;

import com.gsf.MSVC_Productos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrevisionRepository extends JpaRepository<Producto, Long> {
}
