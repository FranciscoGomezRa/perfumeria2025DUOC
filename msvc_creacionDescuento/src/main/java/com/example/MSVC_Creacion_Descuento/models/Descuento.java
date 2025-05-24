package com.example.MSVC_Creacion_Descuento.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.Date;

@Entity
@Table(name = "descuentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="id_descuento")
    private Long idDescuento;

    @NotNull(message = "El campo Codigo_Promocional no puede estar vacío") //En caso de usar String, se pone "NOT BLANK"//
    @NotNull
    @Column (name = "Codigo_promocional",nullable = false)
    private Long codigoPromocional;

    @NotNull(message = "El campo Porcentaje_Descuento no puede estar vacío")
    @NotNull
    @Column (name = "Porcentaje_Descuento",nullable = false)
    private Float porcentajeDescuento;

    @NotNull(message = "El campo Fecha_inicio no puede estar vacío")
    @NotNull
    @Column (name = "Fecha_Inicio",nullable = false)
    private Date fechaInicio;

    @NotNull(message = "El campo Fecha_Fin no puede estar vacío")
    @Column (name = "Fecha_Fin",nullable = false)
    private Date fechaFin;

}










