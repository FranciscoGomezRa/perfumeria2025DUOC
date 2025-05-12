package com.gsf.msvc_productos.service;

import com.gsf.msvc_productos.exceptions.ProductoException;
import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {return this.productoRepository.findAll();}

    @Override
    public Producto findById(Long id) {

        return this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException("El producto con id" + id + "no se encuentra registrado")

        );
    }

    @Override
    public Producto save(Producto producto) {
        if(productoRepository.findByNombreProducto(producto.getNombreProducto()).isPresent()){
            throw new ProductoException("El producto"+producto.getNombreProducto()+ "ya existe");
        }
        return this.productoRepository.save(producto) ;
    }

    @Override
    public void deleteById(Long id){
        if(this.productoRepository.findById(id).isPresent()){
            this.productoRepository.deleteById(id);
        }else{
            throw new ProductoException("No se muestra el producto con:" +id);
        }
    }

}
