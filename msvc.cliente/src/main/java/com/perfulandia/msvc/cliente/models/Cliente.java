package com.perfulandia.msvc.cliente.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name ="clientes")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message = "El campo Rut de cliente, no puede ser vacío")
    @Pattern(regexp = "([1-9]{1}|[1-9]\\dKk)", message = "El formato de rut del cliente debe ser xxxxxxxx-x" )
    @Column(nullable = false, unique = true)
    private String rut;

    @NotBlank(message = "El campo del nombre no puede ser vacío ")
    @Column(nullable = false)
    private String nombreCliente;

    @NotNull(message = "El campo email cliente no puede ser vacío") //En caso de usar String, se pone "NOT BLANK"//
    @NotNull
    @Column (name = "Email_Cliente",nullable = false)
    private Long emailCliente;

}
