package com.perfulandia.msvc.cliente.assemblers;

import com.perfulandia.msvc.cliente.controllers.ClienteControllerv2;
import com.perfulandia.msvc.cliente.models.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {


    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ClienteControllerv2.class).findById(entity.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerv2.class).findAll()).withRel("Clientes"));

    }
}
