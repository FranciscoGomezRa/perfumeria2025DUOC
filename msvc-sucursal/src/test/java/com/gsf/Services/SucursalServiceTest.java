package com.gsf.Services;


import com.gsf.msvc_sucursal.exceptions.SucursalException;

import com.gsf.msvc_sucursal.model.Sucursal;
import com.gsf.msvc_sucursal.repositories.SucursalRepository;
import com.gsf.msvc_sucursal.service.SucursalServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;



    @InjectMocks
    private SucursalServiceImpl sucursalService;

    private Sucursal sucursalPrueba;
    private Sucursal sucursalPruebaCamposIncompletos = new Sucursal();

    private List<Sucursal> sucursales = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.sucursalPrueba = new Sucursal(
               "Sucursal Vina Centro","Libertad 833"
        );
        Faker faker = new Faker(Locale.of("es","CL"));

        if(sucursalRepository.count() == 0){
            for(int i=0;i<100;i++){
                Sucursal sucursal = new Sucursal();

                sucursal.setNombreSucursal(faker.address().cityPrefix());
                sucursal.setDireccionSucursal(faker.address().city());
                sucursales.add(sucursal);

            }
        }
    }

    @Test
    @DisplayName("Debo listar todas las sucursales")
    public void shouldFindAllSucursales(){

        List<Sucursal> sucursales = this.sucursales;
        sucursales.add(sucursalPrueba);
        when(sucursalRepository.findAll()).thenReturn(sucursales);

        List<Sucursal> result = sucursalService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(sucursalPrueba);

        verify(sucursalRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un sucursal")
    public void shouldFindById(){
        when(sucursalRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(sucursalPrueba));

        Sucursal result = sucursalService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).findById(Long.valueOf(1L));

    }



    @Test
    @DisplayName("Debe buscar un sucursal un Id que no existe")
    public void shouldNotFindSucursalId(){
        Long idInexistente = (Long) 999L;
        when(sucursalRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            sucursalService.findById(idInexistente);
        }).isInstanceOf(SucursalException.class)
                .hasMessageContaining("la Sucursal con id " +
                        idInexistente + " no se encuentra en la base de datos");
        verify(sucursalRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva sucursal")
    public void shouldSaveSucursal(){
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);
        Sucursal result = sucursalService.save(sucursalPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).save(any(Sucursal.class));
    }

    @Test
    @DisplayName("Debe Actualizar una Sucursal")
    public void shouldUpdateProducto(){
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);
        Sucursal result = sucursalRepository.save(sucursalPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).save(any(Sucursal.class));
    }

    @Test
    @DisplayName("Debe Eliminar un Cliente existente")
    public void shouldDeleteExistingCliente() {
        when(sucursalRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(sucursalPrueba));  // Primero verifica que existe
        doNothing().when(sucursalRepository).deleteById(1L);

        Sucursal result = sucursalService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);

        sucursalService.deleteById(1L);
        verify(sucursalRepository, times(1)).deleteById(1L);
    }


}
