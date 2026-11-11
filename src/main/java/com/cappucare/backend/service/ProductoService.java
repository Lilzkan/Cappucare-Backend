package com.cappucare.backend.service;

import com.cappucare.backend.exception.RecursoNoEncontradoException;
import com.cappucare.backend.model.Producto;
import com.cappucare.backend.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(String id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producto no encontrado con ID/SKU: " + id));
    }

    public Producto crearOActualizar(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (producto.getVersion() == null) {
            producto.setVersion(1L);
        }
        if (producto.getEstado() == null) {
            producto.setEstado("Disponible");
        }
        return productoRepository.save(producto);
    }

    public Producto actualizar(String id, Producto productoActualizado) {
        Producto existente = buscarPorId(id);
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setCategoria(productoActualizado.getCategoria());
        existente.setCantidadDisponible(productoActualizado.getCantidadDisponible());
        existente.setUnidadMedida(productoActualizado.getUnidadMedida());
        existente.setStockMinimo(productoActualizado.getStockMinimo());
        existente.setUbicacion(productoActualizado.getUbicacion());
        existente.setEstado(productoActualizado.getEstado());
        // Se respeta el version para optimistic locking
        if (productoActualizado.getVersion() != null) {
            existente.setVersion(productoActualizado.getVersion());
        }
        return productoRepository.save(existente);
    }

    public void eliminar(String id) {
        if (!productoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Producto no encontrado con ID/SKU: " + id);
        }
        productoRepository.deleteById(id);
    }
}
