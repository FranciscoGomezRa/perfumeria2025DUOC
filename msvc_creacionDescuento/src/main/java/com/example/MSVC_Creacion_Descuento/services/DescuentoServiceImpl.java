package com.example.MSVC_Creacion_Descuento.services;


import com.example.MSVC_Creacion_Descuento.exceptions.DescuentoException;
import com.example.MSVC_Creacion_Descuento.models.Descuento;
import com.example.MSVC_Creacion_Descuento.repositories.DescuentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DescuentoServiceImpl implements DescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;

    @Transactional
    @Override
    public List<Descuento> findAll() { return this.descuentoRepository.findAll();}

    @Transactional
    @Override
    public Descuento findById(Long id) {
        return this.descuentoRepository.findById(id).orElseThrow(
                () -> new DescuentoException("El descuento con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Transactional
    @Override
    public Descuento save(Descuento descuento){
        if (descuento.getCodigoPromocional() != null && this.descuentoRepository.findByCodigoPromocional(descuento.getCodigoPromocional()).isPresent()){
            throw new DescuentoException("El descuento con el nombre: " + descuento.getCodigoPromocional() + " ya existe en la base de datos");//Este codigo sirve para UNIQUES para ID
        }

        return this.descuentoRepository.save(descuento);
    }

    @Transactional
    @Override
    public void deleteById(Long id){
        this.descuentoRepository.deleteById(id);
    }







}
