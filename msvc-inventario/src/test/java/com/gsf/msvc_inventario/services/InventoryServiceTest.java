package com.gsf.msvc_inventario.services;


import com.gsf.msvc_inventario.client.ProductClientRest;
import com.gsf.msvc_inventario.client.SucursalClientRest;
import com.gsf.msvc_inventario.exception.InventoryException;
import com.gsf.msvc_inventario.model.Producto;
import com.gsf.msvc_inventario.model.Sucursal;
import com.gsf.msvc_inventario.model.entity.Inventario;
import com.gsf.msvc_inventario.repository.InventoryRepository;
import com.gsf.msvc_inventario.service.InventoryServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductClientRest productClientRest;
    @Mock
    private SucursalClientRest sucursalClientRest;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    private Inventario inventarioPrueba;


    private List<Inventario> inventarios = new ArrayList<>();
    private List<Sucursal> sucursales = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.inventarioPrueba = new Inventario(
                5, 1L,1L
        );

        Faker faker = new Faker(Locale.of("es","CL"));

        Set<String> nombresUtilizados= new HashSet<>();
        String nombreSucursal;
        Boolean flag;

        if(inventoryRepository.count()==0){
            for(int i=0;i<100;i++){

                Sucursal sucursal = new Sucursal();
                Producto producto = new Producto();
                Inventario inventario = new Inventario();

                /*CREA 100 SUCURSALES */
                flag=false;
                while (!flag) {
                    nombreSucursal = faker.animal().scientificName();
                    if (!nombresUtilizados.contains(sucursal.getNombreSucursal())) {
                        sucursal.setNombreSucursal(nombreSucursal);
                        sucursal.setDireccionSucursal(faker.address().fullAddress());
                        nombresUtilizados.add(sucursal.getNombreSucursal());
                        flag=true;
                    }
                }
                sucursales.add(sucursal);

                /*CREA 100 PRODUCTOS */
                String precio = faker.commerce().price();
                producto.setNombreProducto(faker.commerce().productName());
                producto.setDescripcionProducto(faker.commerce().brand());
                producto.setPrecioProducto(Float.parseFloat(precio.replace(",",".")));
                productos.add(producto);

                /*ASOCIA 100 INVENTARIOS */
                Integer cantidad = 5;
                inventario.setCantidadInventario(cantidad);
                inventario.setIdProducto(producto.getIdProducto());
                inventario.setIdSucursal(sucursal.getIdSucursal());
                inventarios.add(inventario);

            }
        }
    }

    @Test
    @DisplayName("Debo listar todos los inventarios")
    public void shouldFindAllInventarios(){

        List<Inventario> Inventarios = this.inventarios;
        Inventarios.add(inventarioPrueba);
        when(inventoryRepository.findAll()).thenReturn(Inventarios);

        List<Inventario> result = inventoryService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(inventarioPrueba);

        verify(inventoryRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un Inventario")
    public void shouldFindById(){
        when(inventoryRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(inventarioPrueba));

        Inventario result = inventoryService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inventarioPrueba);
        verify(inventoryRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar un Inventario un Id que no existe")
    public void shouldNotFindInventarioId(){
        Long idInexistente = (Long) 999L;
        when(inventoryRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            inventoryService.findById(idInexistente);
        }).isInstanceOf(InventoryException.class)
                .hasMessageContaining("El producto no existe en la sucursal");
        verify(inventoryRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo Inventario")
    public void shouldSaveInventario(){

        // Realiza las validaciones
        when(productClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(productos.getFirst()));
        when(sucursalClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(sucursales.getFirst()));

        // Realiza el guardada
        when(inventoryRepository.save(any(Inventario.class))).thenReturn(inventarioPrueba);

        // Llamo al servicio
        Inventario result = inventoryService.save(inventarioPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inventarioPrueba);

        verify(productClientRest, times(1)).findById(Long.valueOf(1L));
        verify(sucursalClientRest, times(1)).findById(Long.valueOf(1L));
        verify(inventoryRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    @DisplayName("Debe Actualizar un Inventario")
    public void shouldUpdateInventario(){
        when(productClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(productos.getFirst()));
        when(sucursalClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(sucursales.getFirst()));

        when(inventoryRepository.save(any(Inventario.class))).thenReturn(inventarioPrueba);
        Inventario result = inventoryService.save(inventarioPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inventarioPrueba);
        verify(inventoryRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    @DisplayName("Debe Eliminar un Inventario existente")
    public void shouldDeleteExistingInventario() {
        when(inventoryRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(inventarioPrueba));  // Primero verifica que existe
        doNothing().when(inventoryRepository).deleteById(1L);      // Configura el delete (void)
        inventoryService.deleteById(1L);

        Long idInexistente = (Long) 1L;
        when(inventoryRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            inventoryService.findById(idInexistente);
        }).isInstanceOf(InventoryException.class)
                .hasMessageContaining("El producto no existe en la sucursal");
        verify(inventoryRepository, times(2)).findById(idInexistente);

        verify(inventoryRepository, times(1)).deleteById(1L);
    }

}
