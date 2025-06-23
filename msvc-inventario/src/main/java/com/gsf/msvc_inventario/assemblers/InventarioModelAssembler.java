package com.gsf.msvc_inventario.assemblers;

import com.gsf.msvc_inventario.controller.ProductoSucursalControllerV2;
import com.gsf.msvc_inventario.model.entity.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario entity) {

        Link linkProductos = Link.of("http://localhost:8080/api/v1/productos/"+entity.getIdProducto()).withRel("producto");
        Link linkSucursal = Link.of("http://localhost:8083/api/v1/sucursal/"+entity.getIdSucursal()).withRel("sucursal");
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoSucursalControllerV2.class).findById(entity.getIdInventario())).withSelfRel(),
                linkTo(methodOn(ProductoSucursalControllerV2.class).findAll()).withRel("inventarios"),
                linkProductos,
                linkSucursal

        );
    }
}
