package com.gsf.msvc_sucursal.service;

import com.gsf.msvc_sucursal.model.Sucursal;

import java.util.List;

public interface SucursalService {

Sucursal findById(Long id);
List<Sucursal> findAll() ;
Sucursal save (Sucursal sucursal);
void deleteById (Long id);


}
