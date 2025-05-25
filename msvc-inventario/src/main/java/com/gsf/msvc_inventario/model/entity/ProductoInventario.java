package com.gsf.msvc_inventario.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name="Producto_Sucursal")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@ToString
public class ProductoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_productoSucursal")
    private Long idProductoSucursal;

    @Column(name="cantidad_producto")
    @NotNull(message="Debe Ingresar una Cantidad")
    private Integer cantidadProducto;

    @Column(name="nombre_producto")
    @NotNull(message="Debe Ingresar un nombre de Producto")
    private String nombreProductoSucursal;

    @Column(name="id_producto", nullable = false)
    @NotNull(message="El campo id_producto no puede estar vacio")
    private Long idProducto;




}
