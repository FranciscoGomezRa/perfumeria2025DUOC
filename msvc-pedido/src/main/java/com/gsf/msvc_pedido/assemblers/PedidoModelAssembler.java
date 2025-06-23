package com.gsf.msvc_pedido.assemblers;

import com.gsf.msvc_pedido.controller.PedidoControllerV2;
import com.gsf.msvc_pedido.model.entity.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido entity) {

        Link linkCliente = Link.of("http://localhost:8086/api/v1/cliente/"+entity.getIdCliente()).withRel("Cliente");
        Link linkSucursal = Link.of("http://localhost:8083/api/v1/sucursal/"+entity.getIdSucursal()).withRel("sucursal");
        return EntityModel.of(
                entity,
                linkTo(methodOn(PedidoControllerV2.class).findById(entity.getIdPedido())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).findAll()).withRel("pedidos"),
                linkCliente,
                linkSucursal

        );
    }
}
