package com.gsf.msvc_productos.services;


import com.gsf.msvc_productos.exceptions.ProductoException;
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
    @DisplayName("Debo listar todos los productos")
    public void shouldFindAllProductos(){

        List<Producto> productos = this.productos;
        productos.add(productoPrueba);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = productoService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(productoPrueba);

        verify(productoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un producto")
    public void shouldFindById(){
        when(productoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(productoPrueba));

        Producto result = productoService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoPrueba);
        verify(productoRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar un producto un Id que no existe")
    public void shouldNotFindProductoId(){
        Long idInexistente = (Long) 999L;
        when(productoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            productoService.findById(idInexistente);
        }).isInstanceOf(ProductoException.class)
                .hasMessageContaining("El producto con id " +
                        idInexistente + " no se encuentra en la base de datos");
        verify(productoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo producto")
    public void shouldSaveProducto(){
        when(productoRepository.save(any(Producto.class))).thenReturn(productoPrueba);
        Producto result = productoService.save(productoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoPrueba);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }


}
