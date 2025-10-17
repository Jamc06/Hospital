package models.citas;

import models.medicos.Medico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cita {
    private int idCita;
    private String nombrePaciente;
    private Medico medicoAsignado;
    private LocalDateTime fechaHora;
    private String tipoCita; // consulta general, cirugia, terapia, diagnostico
    private EstadoCita estado;
    private List<String> historialReagendamientos; // registro simple de cambios

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Cita(int idCita, String nombrePaciente, Medico medicoAsignado, LocalDateTime fechaHora,
                String tipoCita, EstadoCita estado) {
        this.idCita = idCita;
        this.nombrePaciente = nombrePaciente;
        this.medicoAsignado = medicoAsignado;
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.estado = estado;
        this.historialReagendamientos = new ArrayList<>();
    }

    public int getIdCita() { return idCita; }
    public String getNombrePaciente() { return nombrePaciente; }
    public Medico getMedicoAsignado() { return medicoAsignado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getTipoCita() { return tipoCita; }
    public EstadoCita getEstado() { return estado; }

    public void setMedicoAsignado(Medico medicoAsignado) { this.medicoAsignado = medicoAsignado; }
    public void setFechaHora(LocalDateTime fechaHora) {
        String before = this.fechaHora.format(fmt);
        this.fechaHora = fechaHora;
        String after = this.fechaHora.format(fmt);
        historialReagendamientos.add(String.format("Reagendada: %s -> %s", before, after));
    }
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public List<String> getHistorialReagendamientos() { return historialReagendamientos; }

    @Override
    public String toString() {
        return String.format("Cita[%d] Paciente: %s | Medico: %s | FechaHora: %s | Tipo: %s | Estado: %s",
                idCita, nombrePaciente, medicoAsignado.getNombre(),
                fechaHora.format(fmt), tipoCita, estado.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cita)) return false;
        Cita cita = (Cita) o;
        return idCita == cita.idCita;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCita);
    }
}
