package com.gsf.msvc_detallepedido.services;



import com.gsf.msvc_detallepedido.clients.InventarioClientRest;
import com.gsf.msvc_detallepedido.clients.PedidoClientRest;
import com.gsf.msvc_detallepedido.clients.ProductoClientRest;
import com.gsf.msvc_detallepedido.clients.SucursalClientRest;
import com.gsf.msvc_detallepedido.dtos.BuscaStockPorIdDTO;
import com.gsf.msvc_detallepedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_detallepedido.dtos.idPedidoDTO;
import com.gsf.msvc_detallepedido.exceptions.DetallePedidoException;
import com.gsf.msvc_detallepedido.model.*;
import com.gsf.msvc_detallepedido.model.entity.DetallePedido;
import com.gsf.msvc_detallepedido.repository.DetallePedidoRepository;
import com.gsf.msvc_detallepedido.service.DetallePedidoServiceImpl;
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
public class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository detallePedidoRepository;

    @Mock
    private PedidoClientRest pedidoClientRest;

    @Mock
    private ProductoClientRest productClientRest;
    @Mock
    private InventarioClientRest inventarioClientRest;
    @Mock
    private SucursalClientRest sucursalClientRest;

    @InjectMocks
    private DetallePedidoServiceImpl detallePedidoService;

    private DetallePedido detallePedidoPrueba = new DetallePedido();
    private Pedido pedidoPrueba = new Pedido();


    private List<DetallePedido> detallePedidos = new ArrayList<>();
    private List<Inventario> inventarios = new ArrayList<>();
    private List<Sucursal> sucursales = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.detallePedidoPrueba = new DetallePedido(1L,1L,1L,1,1F

        );

        Faker faker = new Faker(Locale.of("es","CL"));

        Set<String> nombresUtilizados= new HashSet<>();
        String nombreSucursal;
        Boolean flag;


        this.pedidoPrueba.setIdPedido(1L);
        this.pedidoPrueba.setIdCliente(1L);
        this.pedidoPrueba.setIdSucursal(1L);
        BuscaStockPorIdDTO buscaStockPorIdDTO = new BuscaStockPorIdDTO(1L,1L,1);



        Cliente clientePrueba = new Cliente();
        clientePrueba.setIdCliente(1L);
        clientePrueba.setNombreCliente(faker.name().fullName());
        clientePrueba.setEmailCliente(faker.siliconValley().email());
        clientePrueba.setRut("17.753.455-2");


        if(detallePedidoRepository.count()==0){
            for(int i=0;i<100;i++){

                Sucursal sucursal = new Sucursal();
                Producto producto = new Producto();
                Inventario inventario = new Inventario();


                DetallePedido detallePedido = new DetallePedido();

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

                detallePedido.setIdPedido(this.pedidoPrueba.getIdPedido());
                detallePedido.setIdProducto(producto.getIdProducto());
                detallePedido.setCantidad(1);
                detallePedido.setTotal(producto.getPrecioProducto()*detallePedido.getCantidad());


                PedidoCompletoDTO pedidocompleto = new PedidoCompletoDTO();

                List<DetallePedido> listaDetallePedidos = this.detallePedidoRepository.findByIdPedido(1L);
                Double totalPedido = listaDetallePedidos.stream()
                        .mapToDouble(DetallePedido::getTotal) // Convierte a DoubleStream
                        .sum();

                pedidocompleto.setIdPedido(this.pedidoPrueba.getIdPedido());
                pedidocompleto.setNombreCliente(clientePrueba.getNombreCliente());

                pedidocompleto.setListaDetallePedidos(listaDetallePedidos);
                pedidocompleto.setTotalPedido(totalPedido);

                this.pedidoPrueba.setTotalPedido(pedidocompleto.getTotalPedido());



                detallePedidos.add(detallePedido);


            }
        }
    }

    @Test
    @DisplayName("Debo listar todos los detallePedidos")
    public void shouldFindAllDetallePedidos(){

        List<DetallePedido> DetallePedidos = this.detallePedidos;
        DetallePedidos.add(detallePedidoPrueba);
        when(detallePedidoRepository.findAll()).thenReturn(DetallePedidos);

        List<DetallePedido> result = detallePedidoService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(detallePedidoPrueba);

        verify(detallePedidoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un DetallePedido")
    public void shouldFindById(){
        when(detallePedidoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(detallePedidoPrueba));

        DetallePedido result = detallePedidoService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(detallePedidoPrueba);
        verify(detallePedidoRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar un DetallePedido un Id que no existe")
    public void shouldNotFindDetallePedidoId(){
        Long idInexistente = (Long) 999L;
        when(detallePedidoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            detallePedidoService.findById(idInexistente);
        }).isInstanceOf(DetallePedidoException.class)
                .hasMessageContaining("El Detalle Pedido no existe en la base de datos");
        verify(detallePedidoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo DetallePedido")
    public void shouldSaveDetallePedido() {

        BuscaStockPorIdDTO buscaStockDTO = new BuscaStockPorIdDTO(1L, 1L, 1); // Ajusta los valores según tu DTO

        when(productClientRest.findById(1L)).thenReturn(ResponseEntity.ok(productos.getFirst()));
        when(pedidoClientRest.findById(1L)).thenReturn(ResponseEntity.ok(this.pedidoPrueba));
        when(sucursalClientRest.findById(1L)).thenReturn(ResponseEntity.ok(sucursales.getFirst()));


        when(inventarioClientRest.stockInventario(any(BuscaStockPorIdDTO.class)))
                .thenReturn(ResponseEntity.ok(true));

        when(detallePedidoRepository.save(any(DetallePedido.class))).thenReturn(detallePedidoPrueba);


        DetallePedido result = detallePedidoService.save(detallePedidoPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(detallePedidoPrueba);

        verify(productClientRest, times(1)).findById(1L);
        verify(pedidoClientRest, times(1)).findById(1L);
        verify(sucursalClientRest, times(1)).findById(1L);
        verify(inventarioClientRest, times(1)).stockInventario(any(BuscaStockPorIdDTO.class));
        verify(detallePedidoRepository, times(1)).save(any(DetallePedido.class));
    }

    @Test
    @DisplayName("Debe Actualizar un DetallePedido")
    public void shouldUpdateDetallePedido(){

        when(pedidoClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(pedidoPrueba));


        when(inventarioClientRest.stockInventario(any(BuscaStockPorIdDTO.class)))
                .thenReturn(ResponseEntity.ok(true));

        when(productClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(productos.getFirst()));
        when(sucursalClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(sucursales.getFirst()));

        when(detallePedidoRepository.save(any(DetallePedido.class))).thenReturn(detallePedidoPrueba);
        DetallePedido result = detallePedidoService.save(detallePedidoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(detallePedidoPrueba);
        verify(detallePedidoRepository, times(1)).save(any(DetallePedido.class));
    }

    @Test
    @DisplayName("Debe Eliminar un DetallePedido existente")
    public void shouldDeleteExistingDetallePedido() {
        when(detallePedidoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(detallePedidoPrueba));  // Primero verifica que existe
        doNothing().when(detallePedidoRepository).deleteById(1L);      // Configura el delete (void)
        detallePedidoService.deleteById(1L);
        verify(detallePedidoRepository, times(1)).deleteById(1L);
    }
}
