package com.gsf.msvc_sucursal.assamblers;

import com.gsf.msvc_sucursal.controllers.SucursalControllerv2;
import com.gsf.msvc_sucursal.model.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {


    @Override
    public EntityModel<Sucursal> toModel(Sucursal entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(SucursalControllerv2.class).findById(entity.getIdSucursal())).withSelfRel(),
                linkTo(methodOn(SucursalControllerv2.class).findAll()).withRel("Sucursales"));

    }
}
