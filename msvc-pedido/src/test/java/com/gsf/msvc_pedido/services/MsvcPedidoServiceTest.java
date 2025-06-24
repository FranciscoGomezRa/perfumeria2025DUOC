package com.gsf.msvc_pedido.services;

import com.gsf.msvc_pedido.clients.ClienteClientRest;
import com.gsf.msvc_pedido.clients.DetallePedidoClientRest;
import com.gsf.msvc_pedido.clients.InventarioClientRest;
import com.gsf.msvc_pedido.clients.SucursalClientRest;
import com.gsf.msvc_pedido.dtos.PedidoCompletoDTO;
import com.gsf.msvc_pedido.dtos.PedidoDTO;
import com.gsf.msvc_pedido.dtos.idPedidoDTO;
import com.gsf.msvc_pedido.exception.PedidoException;
import com.gsf.msvc_pedido.model.*;
import com.gsf.msvc_pedido.model.entity.Pedido;
import com.gsf.msvc_pedido.repository.PedidoRepository;
import com.gsf.msvc_pedido.service.PedidoServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MsvcPedidoServiceTest {
/*idPedido idCliente idSucursal */
    /*CADA WHEN TIENE QUE ESTAR RELACIONADO CON UN VERIFY*/
    
    @Mock
    private PedidoRepository pedidoRepository;


    @Mock
    private ClienteClientRest clienteClientRest;
    @Mock
    private DetallePedidoClientRest detallePedidoClientRest;
    @Mock
    private InventarioClientRest inventarioClientRest;
    @Mock
    private SucursalClientRest sucursalClientRest;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Pedido pedidoPrueba = new Pedido();
    private Cliente clientePrueba = new Cliente();
    private Sucursal sucursalPrueba = new Sucursal();



    private List<Pedido> pedidos = new ArrayList<>();
    private List<Inventario> inventarios = new ArrayList<>();
    private List<Sucursal> sucursales = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        // Configuración básica del pedido de prueba
        this.pedidoPrueba = new Pedido(1L, 1L);

        this.sucursalPrueba = new Sucursal(1L,"La mejor sucursal", "Algun lugar");
        sucursales.add(sucursalPrueba);
        // Configuración del mock para findAll
        List<Pedido> pedidosMock = new ArrayList<>();
        pedidosMock.add(pedidoPrueba); // Solo añade el pedido de prueba

        when(pedidoRepository.findAll()).thenReturn(pedidosMock);

        // Elimina todo el código restante del setUp que interactúa con los repositorios
        // Los mocks no necesitan inicialización de datos como un repositorio real
    }

    @Test
    @DisplayName("Debo listar todos los pedidos")
    public void shouldFindAllPedidos() {
        // Ejecución
        List<Pedido> result = pedidoService.findAll();

        // Verificaciones
        assertThat(result).hasSize(1); // Solo debería devolver el pedidoPrueba
        assertThat(result).containsExactly(pedidoPrueba);

        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar un Pedido por ID existente")
    public void shouldFindPedidoById() {
        // Given
        Long idExistente = 1L;
        when(pedidoRepository.findById(idExistente)).thenReturn(Optional.of(pedidoPrueba));

        // When
        Pedido result = pedidoService.findById(idExistente);

        // Then
        assertThat(result).isEqualTo(pedidoPrueba);
        verify(pedidoRepository).findById(idExistente);
    }

    @Test
    @DisplayName("Debe buscar un Pedido un Id que no existe")
    public void shouldNotFindPedidoId(){
        Long idInexistente = (Long) 999L;
        when(pedidoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            pedidoService.findById(idInexistente);
        }).isInstanceOf(PedidoException.class)
                .hasMessageContaining("El Pedido no existe en la base de datos");
        verify(pedidoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo Pedido válido")
    public void shouldSaveValidPedido() {

        PedidoDTO pedidoDTO = new PedidoDTO(1L, 1L);

        when(clienteClientRest.findById(1L)).thenReturn(ResponseEntity.ok(clientePrueba));
        when(sucursalClientRest.findById(1L)).thenReturn(ResponseEntity.ok(sucursales.getFirst()));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoPrueba);


        Pedido result = pedidoService.save(pedidoDTO);


        assertThat(result)
                .isNotNull()
                .isEqualTo(pedidoPrueba);

        verify(clienteClientRest).findById(1L);
        verify(sucursalClientRest).findById(1L);
        verify(pedidoRepository).save(any(Pedido.class));
    }
    @Test
    @DisplayName("Debe lanzar excepción cuando la Sucursal no existe al guardar pedido")
    public void shouldThrowExceptionWhenSucursalNotFoundOnSave() {
        Long idSucursalInexistente = 999L;
        PedidoDTO dto = new PedidoDTO();
        dto.setIdSucursal(idSucursalInexistente);
        dto.setIdCliente(1L);

        when(sucursalClientRest.findById(idSucursalInexistente))
                .thenReturn(ResponseEntity.notFound().build());

        // xD
        assertThatThrownBy(() -> pedidoService.save(dto))
                .isInstanceOf(PedidoException.class) // Ajusta según tu excepción
                .hasMessageContaining("Sucursal con id "+idSucursalInexistente +" encontrada");

        // Verifies
        verify(sucursalClientRest, times(1)).findById(idSucursalInexistente);
        verify(clienteClientRest, never()).findById(any());
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe Actualizar un Pedido")
    public void shouldUpdatePedido(){

        when(clienteClientRest.findById(Long.valueOf(1L))).thenReturn(ResponseEntity.ok(clientePrueba));
        when(sucursalClientRest.findById(1L)).thenReturn(ResponseEntity.ok(sucursales.getFirst()));

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoPrueba);
        Pedido result = pedidoService.save(new PedidoDTO(pedidoPrueba.getIdCliente(), pedidoPrueba.getIdSucursal()));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(pedidoPrueba);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }
    @Test
    @DisplayName("Debe lanzar PedidoException al actualizar pedido inexistente")
    public void shouldThrowPedidoExceptionWhenUpdatingNonExistentPedido() {

        Long idPedidoInexistente = 999L;
        PedidoDTO dto = new PedidoDTO();

        when(pedidoRepository.findById(idPedidoInexistente))
                .thenReturn(Optional.empty());

        // :3
        assertThatThrownBy(() -> pedidoService.update(idPedidoInexistente, dto))
                .isInstanceOf(PedidoException.class)
                .hasMessageContaining("El Pedido no existe en la base de datos");

        // Verifkies
        verify(pedidoRepository, times(1)).findById(idPedidoInexistente);
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe Eliminar un Pedido existente")
    public void shouldDeleteExistingPedido() {
        when(pedidoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(pedidoPrueba));  // Primero verifica que existe
        doNothing().when(pedidoRepository).deleteById(1L);      // Configura el delete (void)
        pedidoService.deleteById(1L);

        Long idInexistente = (Long) 1L;
        when(pedidoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            pedidoService.findById(idInexistente);
        }).isInstanceOf(PedidoException.class)
                .hasMessageContaining("El Pedido no existe en la base de datos");
        verify(pedidoRepository, times(2)).findById(idInexistente);

        verify(pedidoRepository, times(1)).deleteById(1L);
    }
    @Test
    @DisplayName("Debe lanzar PedidoException cuando no encuentra el Pedido para emisión total")
    public void shouldThrowPedidoExceptionWhenPedidoNotFoundForEmisionTotal() {
        // Arrange
        Long idPedidoInexistente = 999L;
        idPedidoDTO dto = new idPedidoDTO(idPedidoInexistente);

        when(pedidoRepository.findById(idPedidoInexistente))
                .thenReturn(Optional.empty());

        // asddas
        assertThatThrownBy(() -> pedidoService.emisionTotalPedidos(dto))
                .isInstanceOf(PedidoException.class)
                .hasMessageContaining("El Pedido no existe en la base de datos");

        // Verify
        verify(pedidoRepository, times(1)).findById(idPedidoInexistente);
        verify(clienteClientRest, never()).findById(any());
        verify(detallePedidoClientRest, never()).BuscadorPorIdPedido(any());
    }
    @Test
    @DisplayName("Debe manejar lista vacía de detalles en emisionPedidoCalculado")
    public void shouldHandleEmptyDetalleListInEmisionPedidoCalculado() {

        Long idPedidoExistente = 1L;
        Pedido pedidoMock = new Pedido();
        pedidoMock.setIdCliente(1L);

        when(pedidoRepository.findById(idPedidoExistente))
                .thenReturn(Optional.of(pedidoMock));
        when(clienteClientRest.findById(anyLong()))
                .thenReturn(ResponseEntity.ok(new Cliente()));
        when(detallePedidoClientRest.BuscadorPorIdPedido(any()))
                .thenReturn(ResponseEntity.ok(Collections.emptyList()));

        // Act
        PedidoCompletoDTO result = pedidoService.emisionPedidoCalculado(idPedidoExistente);

        // Assert
        assertThat(result.getListaDetallePedidos()).isEmpty();
        assertThat(result.getTotalPedido()).isEqualTo(0.0);
    }
}
