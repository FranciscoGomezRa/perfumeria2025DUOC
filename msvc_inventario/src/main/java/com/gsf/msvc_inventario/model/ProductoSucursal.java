package com.gsf.msvc_inventario.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name="Producto_Sucursal")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@ToString
public class ProductoSucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_productoSucursal")
    private Long idProductoSucursal;

    @Column(name="cantidad_Producto")
    @NotNull(message="Debe Ingresar una Cantidad")
    private Integer cantidadProducto;






}
