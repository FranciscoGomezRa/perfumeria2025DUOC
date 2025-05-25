package com.gsf.MSVC_SUCURSAL.Servicee;

import com.gsf.MSVC_SUCURSAL.exceptions.SucursalException;
import com.gsf.MSVC_SUCURSAL.model.Sucursal;
import com.gsf.MSVC_SUCURSAL.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;


    @Override
    public Sucursal findById(Long id) {
        return this.sucursalRepository.findById(id).orElseThrow(
                () -> new SucursalException("la Sucursal con id " + id + " no se encuentra en la base de datos "));



    }

    @Override
    public List<Sucursal> findAll() {
        return this.sucursalRepository.findAll();
    }


    @Override
        public Sucursal save(Sucursal sucursal) {
            if (this.sucursalRepository.findByNombreSucursal(sucursal.getNombreSucursal()).isPresent()) {
                throw new SucursalException("La sucursal con el nombre: " + sucursal.getNombreSucursal() + " ya existe en la base de datos");
            }
            return this.sucursalRepository.save(sucursal);
        }

    @Override
    public void deleteById(Long id) {
        this.sucursalRepository.deleteById(id);
    }


}
