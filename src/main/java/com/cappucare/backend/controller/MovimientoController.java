package com.cappucare.backend.controller;

import com.cappucare.backend.model.Movimiento;
import com.cappucare.backend.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> listarTodos() {
        List<Movimiento> movimientos = movimientoService.listarTodos();
        return ResponseEntity.ok(movimientos);
    }

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento guardado = movimientoService.registrarMovimiento(movimiento);
        return ResponseEntity.ok(guardado);
    }
}
