package com.gsf.msvc_detallepedido.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor

public class DetallePedido {

    @Id
    @Column(name="id_detallepedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetallePedido;

    @Column(name="id_pedido",nullable = false)
    @NotNull(message="El id_pedido no puede estar vacio")
    private Long idPedido;

    @Column(name="id_producto",nullable = false)
    @NotNull(message="El id_producto no puede estar vacio")
    private Long idProducto;

    @Column(name="id_cantidad")
    @NotNull(message="El id_cantidad no puede estar vacio")
    private Integer cantidad;

    @Column(name="total")
    private Float total;

}
