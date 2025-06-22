package com.perfulandia.msvc.cliente.services;

import com.perfulandia.msvc.cliente.exceptions.ClienteException;
import com.perfulandia.msvc.cliente.models.Cliente;
import com.perfulandia.msvc.cliente.repositories.ClienteRepository;
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
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente clientePrueba;
    private List<Cliente> clientes;

    @BeforeEach
    public void setUp() {
        this.clientes = new ArrayList<>();
        this.clientePrueba = new Cliente(
                "17142360-0", "Simon Villar", "sim.villar@duocuc.cl"
        );

        Faker faker = new Faker(Locale.of("es", "CL"));

        for (int i = 0; i < 100; i++) {
            Cliente cliente = new Cliente();
            String rut = String.valueOf(faker.number().numberBetween(5_000_000, 25_000_000)) + "-" +
                    faker.number().numberBetween(0, 9);
            cliente.setRut(rut);
            cliente.setNombreCliente(faker.name().fullName());
            cliente.setEmailCliente(faker.internet().emailAddress());
            clientes.add(cliente);
        }
    }

    @Test
    @DisplayName("Debe listar todos los clientes")
    public void shouldFindAllClientes() {
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.findAll();

        assertThat(result).hasSize(100);
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe encontrar un cliente por ID")
    public void shouldFindById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clientePrueba));

        Cliente result = clienteService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(clientePrueba);
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el ID no existe")
    public void shouldThrowExceptionWhenIdNotFound() {
        Long idInexistente = 999L;
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.findById(idInexistente))
                .isInstanceOf(ClienteException.class)
                .hasMessageContaining("El cliente con el id " + idInexistente + " no se encuentra registrado.");

        verify(clienteRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo cliente")
    public void shouldSaveCliente() {
        when(clienteRepository.findByRut(clientePrueba.getRut())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clientePrueba);

        Cliente result = clienteService.save(clientePrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(clientePrueba);
        verify(clienteRepository, times(1)).findByRut(clientePrueba.getRut());
        verify(clienteRepository, times(1)).save(clientePrueba);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el RUT ya existe")
    public void shouldThrowExceptionWhenRutExists() {
        when(clienteRepository.findByRut(clientePrueba.getRut())).thenReturn(Optional.of(clientePrueba));

        assertThatThrownBy(() -> clienteService.save(clientePrueba))
                .isInstanceOf(ClienteException.class)
                .hasMessageContaining("El cliente con el rut " + clientePrueba.getRut() + " ya existe en la base de datos.");

        verify(clienteRepository, times(1)).findByRut(clientePrueba.getRut());
        verify(clienteRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe eliminar un cliente por ID")
    public void shouldDeleteById() {
        Long idToDelete = 1L;

        clienteService.deleteById(idToDelete);

        verify(clienteRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    @DisplayName("Debe actualizar un cliente existente")
    public void shouldUpdateCliente() {

    }
}
