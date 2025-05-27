package com.gsf.msvc_inventario.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name="Producto_Sucursal")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@ToString
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_productoSucursal")
    private Long idInventario;

    @Column(name="cantidad_producto")
    @NotNull(message="Debe Ingresar una Cantidad")
    private Integer cantidadInventario;

    @Column(name="id_producto", nullable = false)
    @NotNull(message="El campo id_producto no puede estar vacio")
    private Long idProducto;

    @Column(name="id_sucursal", nullable = false)
    @NotNull(message="El campo id_sucursal no puede estar vacio")
    private Long idSucursal;




}
