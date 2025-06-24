package com.gsf.msvc_pedido.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name="pedido")
@Getter
@Setter
@ToString@AllArgsConstructor@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_pedido")
    @Schema(description = "Entrega el codigo de identificacion de Pedido ",example = "1")
    private Long idPedido;

    @Column(name="id_cliente",nullable=false)
    @NotNull(message="El campo ID cliente no puede estar vacio")
    @Schema(description = "Entrega el codigo de Cliente asociado a un Pedido ",example = "1")
    private Long idCliente;

    @Column(name="id_sucursal",nullable=false)
    @NotNull(message="El campo id_sucursal no puede estar vacio")
    @Schema(description = "Entrega el codigo de Sucursal asociado a un Pedido ",example = "1")
    private Long idSucursal;

    @JsonIgnore
    @Column(name="total_pedido")
    private Double totalPedido;

    public Pedido(Long idCliente, Long idSucursal){
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;

    }


}
