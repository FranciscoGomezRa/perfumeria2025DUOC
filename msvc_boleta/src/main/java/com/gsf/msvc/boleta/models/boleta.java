package com.gsf.msvc.boleta.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "boletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_boleta")
    private Long idBoleta;

    @Column(name = "fecha_boleta", nullable = false)
    private LocalDate fechaBoleta;
    @PrePersist
    public void prePersist(){
        this.fechaBoleta = LocalDate.now();
    }



}

