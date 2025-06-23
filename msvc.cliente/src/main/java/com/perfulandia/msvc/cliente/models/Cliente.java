package com.perfulandia.msvc.cliente.models;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(description = "Entidad que representa Cliente y sus atributos")
public class Cliente {
    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Codigo id del cliente", example = "24")
    private Long idCliente;

    @NotBlank(message = "El campo Rut de cliente, no puede ser vacío")
    @Pattern(regexp = "^\\d{7,8}-[0-9Kk]$", message = "El formato de rut del cliente debe ser xxxxxxxx-x" )
    @Column(name="rut",nullable = false, unique = true)
    @Schema(description = "El digito rut + número verificador", example = "17142360-0")
    private String rut;

    @NotBlank(message ="El campo del nombre no puede ser vacío ")
    @Column(name="nombre_cliente",nullable = false)
    @Schema(description = "Nombre completo cliente", example = "Francisco Villar Pino")
    private String nombreCliente;

    @NotNull(message ="El campo email cliente no puede ser vacío") //En caso de usar String, se pone "NOT BLANK"//
    @NotBlank
    @Column (name ="email_cliente",nullable = false)
    @Schema(description = "Email de contacto del cliente", example="franc.pino@duocuc.cl")
    private String emailCliente;

    public Cliente(String rut, String nombreCliente, String emailCliente) {
        this.rut = rut;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;

    }

}
