package com.gsf.msvc_sucursal.repositories;


import com.gsf.msvc_sucursal.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SucursalRepository extends JpaRepository <Sucursal,Long> {


    Optional<Sucursal> findByNombreSucursal (String nombresucursal);
}
