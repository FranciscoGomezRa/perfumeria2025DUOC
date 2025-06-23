package com.gsf.msvc_detallepedido.model.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Schema(description = "Representa la endidad Detalle Pedido")
public class DetallePedido {

    @Id
    @Column(name="id_detallepedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Entrega el codigo de identificacion de Detalle Pedido ",example = "1")
    private Long idDetallePedido;

    @Column(name="id_pedido",nullable = false)
    @NotNull(message="El id_pedido no puede estar vacio")
    @Schema(description = "Entrega el codigo de Pedido asociado a un Detalle Pedido ",example = "1")
    private Long idPedido;

    @Column(name="id_producto",nullable = false)
    @NotNull(message="El id_producto no puede estar vacio")
    @Schema(description = "Entrega el codigo del producto asociado al Detalle Pedido ",example = "1")
    private Long idProducto;

    @Column(name="id_cantidad")
    @NotNull(message="El id_cantidad no puede estar vacio")
    @Schema(description = "Entrega la cantidad del producto asociado al detalle pedido",example = "10")
    private Integer cantidad;

    @Column(name="total")
    @Schema(description = "Entrega el total ",example = "10000")
    private Float total;

    public DetallePedido(Long idPedido, Long idProducto, Integer cantidad, Float total) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;

    }

}
