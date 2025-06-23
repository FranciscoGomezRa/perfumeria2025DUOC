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

    @Column(name="nombre_sucursal",nullable = false)
    @NotNull(message = "ingrese el nombre de la sucursal")
    private String nombreSucursal;


    @Column(name="direccion_sucursal",nullable=false)
    @NotNull(message ="ingrese una direccion valida" )
    private String direccionSucursal;

    public Sucursal(String nombreSucursal, String direccionSucursal) {
        this.nombreSucursal = nombreSucursal;
        this.direccionSucursal = direccionSucursal;
    }


}
