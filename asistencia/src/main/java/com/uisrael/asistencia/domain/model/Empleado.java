package com.uisrael.asistencia.domain.model;

import com.uisrael.asistencia.domain.valueobject.Email;
import java.time.LocalDateTime;

public class Empleado {

    private Long id;
    private Usuario usuario;
    private String cedula;
    private String nombres;
    private String apellidos;
    private Email correoPersonal;
    private String cargo;
    private String departamento;
    private Horario horario;
    private boolean estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Empleado() {
    }

    public Empleado(Long id, Usuario usuario, String cedula, String nombres,
            String apellidos, Email correoPersonal, String cargo,
            String departamento, Horario horario) {
        this.id = id;
        this.usuario = usuario;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoPersonal = correoPersonal;
        this.cargo = cargo;
        this.departamento = departamento;
        this.horario = horario;
        this.estado = true;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Email getCorreoPersonal() {
        return correoPersonal;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Horario getHorario() {
        return horario;
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
