package com.gsf.msvc_sucursal.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="sucursal")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor

public class Sucursal {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name ="id_sucursal")
private Long idSucursal;


@Column(name="direccion_Sucursal",nullable=false)
@NotNull(message ="ingrese una direccion valida" )
private String direccionSucursal;



@Column(name="nombre_Sucursal",nullable = false)
@NotNull(message = "ingrese el nombre de la sucursal")
private String nombreSucursal;









    //idSucursal// Long
    //direccionSucursal// String
    //nombreSucursal// String








}
