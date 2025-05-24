package com.example.MSVC_Creacion_Descuento.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.Date;

@Entity
@Table(name ="descuentos")
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

    @Column (name = "Codigo_promocional",nullable = false)
    private String codigoPromocional;

    @NotNull(message = "El campo Porcentaje_Descuento no puede estar vacío")

    @Column (name = "Porcentaje_Descuento",nullable = false)
    private Float porcentajeDescuento;

    @Column (name = "Fecha_Inicio",nullable = false)
    @NotNull(message = "El campo Fecha_Inicio no puede estar vacío")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Column (name = "Fecha_Fin",nullable = false)
    @NotNull(message = "El campo Fecha_Fin no puede estar vacío")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

}





