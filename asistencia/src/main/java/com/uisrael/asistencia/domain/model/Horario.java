package com.uisrael.asistencia.domain.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {

    private Long id;
    private String nombre;
    private LocalTime horaEntrada;
    private LocalTime horaSalidaAlmuerzo;
    private LocalTime horaEntradaAlmuerzo;
    private LocalTime horaSalida;
    private int toleranciaMinutos;
    private boolean estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Horario() {
    }

    public Horario(Long id, String nombre, LocalTime horaEntrada, LocalTime horaSalidaAlmuerzo,
            LocalTime horaEntradaAlmuerzo, LocalTime horaSalida, int toleranciaMinutos) {
        this.id = id;
        this.nombre = nombre;
        this.horaEntrada = horaEntrada;
        this.horaSalidaAlmuerzo = horaSalidaAlmuerzo;
        this.horaEntradaAlmuerzo = horaEntradaAlmuerzo;
        this.horaSalida = horaSalida;
        this.toleranciaMinutos = toleranciaMinutos;
        this.estado = true;
    }

    public LocalTime getHoraLimiteEntrada() {
        return horaEntrada.plusMinutes(toleranciaMinutos);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalTime getHoraSalidaAlmuerzo() {
        return horaSalidaAlmuerzo;
    }

    public LocalTime getHoraEntradaAlmuerzo() {
        return horaEntradaAlmuerzo;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public int getToleranciaMinutos() {
        return toleranciaMinutos;
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
