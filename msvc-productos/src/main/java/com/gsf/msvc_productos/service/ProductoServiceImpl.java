package com.gsf.msvc_productos.service;

import com.gsf.msvc_productos.dtos.ProductoUpdateDTO;
import com.gsf.msvc_productos.exceptions.ProductoException;
import com.gsf.msvc_productos.models.Producto;
import com.gsf.msvc_productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {return this.productoRepository.findAll();}

    @Override
    public Producto findById(Long id) {

        return this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException("El producto con id " + id + " no se encuentra en la base de datos")

        );
    }

    @Override
    public Producto save(Producto producto) {
        if(productoRepository.findByNombreProducto(producto.getNombreProducto()).isPresent()){
            throw new ProductoException("El producto"+producto.getNombreProducto()+ "ya existe");
        }
        producto.setNombreProducto(producto.getNombreProducto());
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
    @Override
    public Optional<Producto> findByNombreProducto(String nombreProducto){
        return this.productoRepository.findByNombreProducto(nombreProducto);
    }

    @Override
    public Producto update(Long idProducto, ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow(
                () -> new ProductoException("El producto con id" + idProducto + "no existe")
        );
        if(productoUpdateDTO.getNombreProducto() != null){
            producto.setNombreProducto(productoUpdateDTO.getNombreProducto());
        }
        if(productoUpdateDTO.getDescripcionProducto() != null){
            producto.setDescripcionProducto(productoUpdateDTO.getDescripcionProducto());
        }
        if(productoUpdateDTO.getPrecioProducto() != 0){
            producto.setPrecioProducto(productoUpdateDTO.getPrecioProducto());
        }
        return this.productoRepository.save(producto);
    }
}
