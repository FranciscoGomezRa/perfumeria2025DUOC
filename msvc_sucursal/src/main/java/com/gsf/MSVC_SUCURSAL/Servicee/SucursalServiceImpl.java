package com.gsf.MSVC_SUCURSAL.Servicee;

import com.gsf.MSVC_SUCURSAL.model.Sucursal;
import com.gsf.MSVC_SUCURSAL.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    public SucursalRepository sucursalRepository;


    @Override
    public Sucursal findById(Long id) {
        return null;

    }





}
