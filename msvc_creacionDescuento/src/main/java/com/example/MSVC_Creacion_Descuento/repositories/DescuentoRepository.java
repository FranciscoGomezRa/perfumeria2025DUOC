package com.example.MSVC_Creacion_Descuento.repositories;


import com.example.MSVC_Creacion_Descuento.models.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository


public interface DescuentoRepository extends JpaRepository<Descuento,Long> {
    Optional<Descuento> findByCodigoPromocional(String codigoPromocional);
}
