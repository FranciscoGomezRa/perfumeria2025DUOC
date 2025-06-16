package com.gsf.msvc_productos.assemblers;

import com.gsf.msvc_productos.controller.ProductoControllerV2;
import com.gsf.msvc_productos.models.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {


    @Override
    public static EntityModel<Producto> toModel(Producto entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withRel("productos")
        );
    }
}
