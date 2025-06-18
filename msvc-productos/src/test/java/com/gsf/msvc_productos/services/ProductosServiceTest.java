package com.gsf.msvc_productos.services;


import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.repository.ProductoRepository;
import com.gsf.msvc_productos.service.ProductoServiceImpl;
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
public class ProductosServiceTest {

    @Mock
    private ProductoRepository productoRepository;



    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto productoPrueba;

    private List<Producto> productos = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.productoPrueba = new Producto(
                "Chocman", "Yico yico",2
        );
        Faker faker = new Faker(Locale.of("es","CL"));

        if(productoRepository.count() == 0){
            for(int i=0;i<100;i++){
                Producto producto = new Producto();

                String precio = faker.commerce().price();

                producto.setNombreProducto(faker.commerce().productName());
                producto.setDescripcionProducto(faker.commerce().brand());
                producto.setPrecioProducto(Float.parseFloat(precio.replace(",",".")));
                productos.add(producto);

            }
        }
    }

    @Test
    @DisplayName("Debo listar todos los medicos")
    public void shouldFindAllMedicos(){

        List<Medico> medicos = this.medicos;
        medicos.add(medicoPrueba);
        when(medicoRepository.findAll()).thenReturn(medicos);

        List<Medico> result = productoService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(medicoPrueba);

        verify(medicoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un medico")
    public void shouldFindById(){
        when(medicoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(medicoPrueba));

        Medico result = productoService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(medicoPrueba);
        verify(medicoRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar un medico un I que n existe")
    public void shouldNotFindMedicoId(){
        Long idInexistente = (Long) 999L;
        when(medicoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            productoService.findById(idInexistente);
        }).isInstanceOf(MedicoException.class)
                .hasMessageContaining("El medico con id " +
                        idInexistente + " no se encuentra en la base de datos");
        verify(medicoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo medico")
    public void shouldSaveMedico(){
        when(medicoRepository.save(any(Medico.class))).thenReturn(medicoPrueba);
        Medico result = productoService.save(medicoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(medicoPrueba);
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }


}
