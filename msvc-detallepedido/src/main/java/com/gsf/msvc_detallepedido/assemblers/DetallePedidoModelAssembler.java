package com.gsf.msvc_detallepedido.assemblers;

import com.gsf.msvc_detallepedido.controller.DetallePedidoControllerV2;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DetallePedidoModelAssembler implements RepresentationModelAssembler<DetallePedido, EntityModel<DetallePedido>> {

    @Override
    public EntityModel<DetallePedido> toModel(DetallePedido entity) {

        Link linkProductos = Link.of("http://localhost:8080/api/v1/productos/"+entity.getIdProducto()).withRel("producto");
        Link linkPedido = Link.of("http://localhost:8087/api/v1/pedido/"+entity.getIdPedido()).withRel("pedido");
        return EntityModel.of(
                entity,
                linkTo(methodOn(DetallePedidoControllerV2.class).findById(entity.getIdDetallePedido())).withSelfRel(),
                linkTo(methodOn(DetallePedidoControllerV2.class).findAll()).withRel("DetallePedidos"),
                linkProductos,
                linkPedido

        );
    }
}