package com.gsf.msvc_productos.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="productos")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_producto")
    @Schema(description = "Codigo del producto",example="1")
    private Long idProducto;

    @Column(name= "descripcionProducto", nullable = false)
    @NotNull(message = "El campo Descripcion debe incluir informacion")
    @Schema(description = "Detalle o anotaciones importante del producto",example="Delicioso Bizcocho")
    private String descripcionProducto;

    @Column(name="nombreProducto",nullable = false)
    @NotNull(message="El campo debe Incluir nombre Producto")
    @Schema(description = "Nombre completo Producto", example = "Chocman")
    private String nombreProducto;

    @Column(name="precioProducto")
    @NotNull(message="El campo Precio no puede estar Vacio")
    @Schema(description = "Precio del producto", example="500.0")
    private Float precioProducto;

    public Producto(String nombreProducto, String descripcionProducto, float precioProducto) {
        this.nombreProducto=nombreProducto;
        this.descripcionProducto=descripcionProducto;
        this.precioProducto=precioProducto;
    }

}
