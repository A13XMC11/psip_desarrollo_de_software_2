package com.uisrael.asistencia.domain.model;

import java.time.LocalDateTime;

public class Usuario {

    private Long id;
    private String nombre;
    private String correo;
    private String passwordHash;
    private Rol rol;
    private boolean estado;
    private LocalDateTime ultimoAcceso;
    private int intentosFallidos;
    private LocalDateTime bloqueadoHasta;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String correo, String passwordHash, Rol rol, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estado = estado;
        this.intentosFallidos = 0;
    }

    public boolean estaBloqueado() {
        return bloqueadoHasta != null && bloqueadoHasta.isAfter(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public LocalDateTime getBloqueadoHasta() {
        return bloqueadoHasta;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
