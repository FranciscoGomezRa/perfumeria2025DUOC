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

    private List<Cliente> clientes = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.clientePrueba = new Cliente(
                "17142360-0", "Simon Villar", "sim.villar@duocuc.cl"
        );
        Faker faker = new Faker(Locale.of("es","CL"));

        if(clienteRepository.count() == 0){
            for(int i=0;i<100;i++){
                Cliente cliente = new Cliente();

                String rut = String.valueOf(faker.number().numberBetween(5_000_000, 25_000_000)) + "-" +
                        faker.number().numberBetween(0, 9);

                cliente.setRut(rut);
                cliente.setNombreCliente((faker.name().fullName()));
                cliente.setEmailCliente(faker.internet().emailAddress());
                clientes.add(cliente);

            }
        }
    }

    @Test
    @DisplayName("Debo listar todos los clientes")
    public void shouldFindAllClientes(){

        List<Cliente> clientes = this.clientes;
        clientes.add(clientePrueba);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(clientePrueba);

        verify(clienteRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un cliente")
    public void shouldFindById(){
        when(clienteRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(clientePrueba));

        Cliente result = clienteService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(clientePrueba);
        verify(clienteRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar un cliente un Id que no existe")
    public void shouldNotFindClienteId(){
        Long idInexistente = (Long) 999L;
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            clienteService.findById(idInexistente);
        }).isInstanceOf(ClienteException.class)
                .hasMessageContaining("El cliente con id " +
                        idInexistente + " no se encuentra en la base de datos");
        verify(clienteRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo cliente")
    public void shouldSaveCliente(){
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clientePrueba);
        Cliente result = clienteService.save(clientePrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(clientePrueba);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }


}
