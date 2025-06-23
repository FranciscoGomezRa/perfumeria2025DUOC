package com.gsf.msvc_inventario.model.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name="Producto_Sucursal")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@ToString
@Schema(description = "Entidad que representa un Inventario, Producto Sucursal Cantidad")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_productoSucursal")
    @Schema(description = "Codigo del Inventario", example = "1")
    private Long idInventario;

    @Column(name="cantidad_producto")
    @NotNull(message="Debe Ingresar una Cantidad")
    @Schema(description = "Representa la cantidad de stock del producto del Inventario",example = "10")
    private Integer cantidadInventario;

    @Column(name="id_producto", nullable = false)
    @NotNull(message="El campo id_producto no puede estar vacio")
    @Schema(description = "Representa la ID del producto asociado al inventario", example = "1")
    private Long idProducto;

    @Column(name="id_sucursal", nullable = false)
    @NotNull(message="El campo id_sucursal no puede estar vacio")
    @Schema(description = "Representa la id de la sucursal asociada al inventario",example="2")
    private Long idSucursal;

    public Inventario(Integer cantidadInventario, Long idProducto, Long idSucursal) {
        this.cantidadInventario = cantidadInventario;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
    }


}
