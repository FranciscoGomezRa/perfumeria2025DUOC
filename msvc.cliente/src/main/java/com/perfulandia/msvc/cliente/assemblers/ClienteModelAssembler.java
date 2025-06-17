package com.perfulandia.msvc.cliente.assemblers;

import com.perfulandia.msvc.cliente.controllers.ClienteController;
import com.perfulandia.msvc.cliente.models.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public static EntityModel<Cliente>toModel(Cliente entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(ClienteControllerV2.class).findById(entity.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).findAll()).withRel("clientes")
        );
    }

}
