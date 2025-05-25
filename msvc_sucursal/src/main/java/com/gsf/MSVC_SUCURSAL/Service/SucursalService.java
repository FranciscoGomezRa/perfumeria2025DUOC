package com.gsf.MSVC_SUCURSAL.Service;

import com.gsf.MSVC_SUCURSAL.model.Sucursal;

import java.util.List;

public interface SucursalService {

Sucursal findById(Long id);
List<Sucursal> findAll() ;
Sucursal save (Sucursal sucursal);
void deleteById (Long id);

}
