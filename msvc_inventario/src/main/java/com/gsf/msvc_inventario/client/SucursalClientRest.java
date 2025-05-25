package com.gsf.msvc_inventario.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="msvc_sucursal",url="localhost:8082/api/v1/productos")
public interface SucursalClientRest {
}
