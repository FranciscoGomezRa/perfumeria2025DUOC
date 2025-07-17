package com.gsf.msvc_sucursal.service;

import com.gsf.msvc_sucursal.dtos.SucursalUpdateDTO;
import com.gsf.msvc_sucursal.exceptions.SucursalException;
import com.gsf.msvc_sucursal.model.Sucursal;
import com.gsf.msvc_sucursal.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    @Override
    public Sucursal update(Long id, SucursalUpdateDTO sucursalUpdateDTO) {
        Sucursal sucursal = this.sucursalRepository.findById(id).orElseThrow(
                () -> new SucursalException("La sucursal con id " + id + " no se encuentra registrada.")
        );
        if(sucursalUpdateDTO.getNombreSucursal() != null) {
            sucursal.setNombreSucursal(sucursalUpdateDTO.getNombreSucursal());
        }
        if(sucursalUpdateDTO.getDireccionSucursal() != null) {
            sucursal.setDireccionSucursal(sucursalUpdateDTO.getDireccionSucursal());
        }
        return this.sucursalRepository.save(sucursal);
    }

}
