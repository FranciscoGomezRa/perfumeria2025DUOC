package com.example.MSVC_Creacion_Descuento.services;


import com.example.MSVC_Creacion_Descuento.models.Descuento;

import java.util.List;

public interface DescuentoService {

    List<Descuento> findAll();
    Descuento findById(Long id);
    Descuento save(Descuento descuento);
    void deleteById(Long id);
}
