package com.gsf.msvc_detallepedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcDetallepedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcDetallepedidoApplication.class, args);
	}

}
