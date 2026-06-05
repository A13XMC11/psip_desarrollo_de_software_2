package com.uisrael.asistencia.domain.model;

import com.uisrael.asistencia.domain.valueobject.RangoIp;
import java.time.LocalDateTime;

public class RedAutorizada {

    private Long id;
    private String nombre;
    private String descripcion;
    private String ipPublica;
    private RangoIp rangoIpLocal;
    private String sede;
    private boolean estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RedAutorizada() {
    }

    public RedAutorizada(Long id, String nombre, String descripcion,
            String ipPublica, RangoIp rangoIpLocal, String sede) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ipPublica = ipPublica;
        this.rangoIpLocal = rangoIpLocal;
        this.sede = sede;
        this.estado = true;
    }

    public boolean permiteIp(String ip) {
        if (ipPublica != null && ipPublica.equals(ip)) {
            return true;
        }
        if (rangoIpLocal != null && rangoIpLocal.contiene(ip)) {
            return true;
        }
        return false;
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

    public String getIpPublica() {
        return ipPublica;
    }

    public RangoIp getRangoIpLocal() {
        return rangoIpLocal;
    }

    public String getSede() {
        return sede;
    }

    public boolean isEstado() {
        return estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
