package com.cappucare.backend.service;

import com.cappucare.backend.exception.RecursoNoEncontradoException;
import com.cappucare.backend.exception.StockInsuficienteException;
import com.cappucare.backend.model.Movimiento;
import com.cappucare.backend.model.Producto;
import com.cappucare.backend.model.TipoMovimiento;
import com.cappucare.backend.repository.MovimientoRepository;
import com.cappucare.backend.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository,
                             ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaHora"));
    }

    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        if (movimiento == null) {
            throw new IllegalArgumentException("El movimiento no puede ser nulo");
        }

        Producto producto = productoRepository.findById(movimiento.getProductoId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producto no encontrado con ID: " + movimiento.getProductoId()));

        // Lógica atómica de actualización de stock
        if (movimiento.getTipo() == TipoMovimiento.INGRESO) {
            producto.setCantidadDisponible(producto.getCantidadDisponible() + movimiento.getCantidad());
        } else if (movimiento.getTipo() == TipoMovimiento.SALIDA) {
            if (producto.getCantidadDisponible() == null
                    || producto.getCantidadDisponible() < movimiento.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para realizar la salida");
            }
            producto.setCantidadDisponible(producto.getCantidadDisponible() - movimiento.getCantidad());
        }

        // Incremento de versión (optimistic locking)
        producto.setVersion(producto.getVersion() != null ? producto.getVersion() + 1 : 1L);
        productoRepository.save(producto);

        // Completar campos del movimiento antes de persistir
        movimiento.setProductoNombre(producto.getNombre());
        if (movimiento.getFechaHora() == null) {
            movimiento.setFechaHora(System.currentTimeMillis());
        }
        if (movimiento.getSincronizado() == null) {
            movimiento.setSincronizado(true);
        }

        return movimientoRepository.save(movimiento);
    }
}
