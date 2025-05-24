package com.example.MSVC_Creacion_Descuento.services;


import com.example.MSVC_Creacion_Descuento.exceptions.DescuentoException;
import com.example.MSVC_Creacion_Descuento.models.Descuento;
import com.example.MSVC_Creacion_Descuento.repositories.DescuentoRepository;
import com.perfulandia.msvc.cliente.exceptions.ClienteException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
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
                () -> new ClienteException("El cliente con el id: " + id+" no se encuentra en la base de datos")
        );
    }

    @Transactional
    @Override
    public Descuento save(Descuento descuento){
        if(this.descuentoRepository.findById(descuento.getIdDescuento()).isPresent()){
            throw new DescuentoException("El descuento con el id: " +descuento.getIdDescuento()+" ya existe en la base de datos");
        }
        return this.descuentoRepository.save(descuento);
    }

    @Transactional
    @Override
    public void deleteById(Long id){
        this.descuentoRepository.deleteById(id);
    };







}
