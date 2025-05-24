package com.gsf.MSVC_SUCURSAL.repositories;


import com.gsf.MSVC_SUCURSAL.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SucursalRepository extends JpaRepository <Sucursal,Long> {
   
}
