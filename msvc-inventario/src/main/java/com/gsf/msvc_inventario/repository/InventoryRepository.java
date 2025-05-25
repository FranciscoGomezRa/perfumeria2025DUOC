package com.gsf.msvc_inventario.repository;

import com.gsf.msvc_inventario.model.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventario, Long> {

}
