package com.uisrael.asistencia.domain.model;

import java.time.LocalDateTime;

public class Rol {

    private Long id;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private LocalDateTime createdAt;

    public Rol() {
    }

    public Rol(Long id, String nombre, String descripcion, boolean estado, LocalDateTime createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
