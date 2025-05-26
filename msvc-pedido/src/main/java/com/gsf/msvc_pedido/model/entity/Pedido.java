package com.gsf.msvc_pedido.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="pedido")
@Getter
@Setter
@ToString@AllArgsConstructor@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_pedido")
    private Long idPedido;

    @Column(name="fecha_pedido",nullable=false)
    @NotNull(message="La fecha no puede ser vacio")
    private LocalDate fechaPedido;

    @Column(name="id_cliente",nullable=false)
    @NotNull(message="El campo ID cliente no puede estar vacio")
    private Long idCliente;

    @Column(name="id_inventario",nullable=false)
    @NotNull(message="El campo id_inventario no puede estar vacio")
    private Long idInventario;



}
