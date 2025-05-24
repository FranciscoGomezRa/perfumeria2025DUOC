package com.gsf.msvc_productos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="productos")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_producto")
    private Long idProducto;

    @Column(name= "descripcionProducto", nullable = false)
    @NotNull(message = "El campo Descripcion debe incluir informacion")
    private String descripcionProducto;

    @Column(name="nombreProducto",nullable = false)
    @NotNull(message="El campo debe Incluir nombre Producto")
    private String nombreProducto;

    @Column(name="precioProducto")
    @NotNull(message="El campo Precio no puede estar Vacio")
    private Float precioProducto;


}
