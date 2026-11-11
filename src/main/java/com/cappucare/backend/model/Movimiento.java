package com.cappucare.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private String productoId;

    @Column(name = "producto_nombre", nullable = false)
    private String productoNombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 10)
    private TipoMovimiento tipo;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "fecha_hora", nullable = false)
    private Long fechaHora;

    @Column(name = "usuario_responsable", nullable = false)
    private String usuarioResponsable;

    @Column(name = "sincronizado", nullable = false)
    private Boolean sincronizado = true;

    public Movimiento() {
    }

    public Movimiento(Long id, String productoId, String productoNombre, TipoMovimiento tipo,
                      Integer cantidad, String motivo, Long fechaHora,
                      String usuarioResponsable, Boolean sincronizado) {
        this.id = id;
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.usuarioResponsable = usuarioResponsable;
        this.sincronizado = sincronizado != null ? sincronizado : true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Long fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public Boolean getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Boolean sincronizado) {
        this.sincronizado = sincronizado;
    }
}
