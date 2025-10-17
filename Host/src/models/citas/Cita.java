package models.citas;

import models.medicos.Medico;

public class Cita {
    private int idCita;
    private String nombrePaciente;
    private Medico medicoAsignado;
    private String fecha; // Formato: "YYYY-MM-DD"
    private String hora; //HH:MM"
    private String motivoConsulta; 
    private String estado; // Programada, Completada, Cancelada

    public Cita(int idCita, String nombrePaciente, Medico medicoAsignado, String fecha,
                String motivoConsulta, String estado, String hora) {
        this.idCita = idCita;
        this.nombrePaciente = nombrePaciente;
        this.medicoAsignado = medicoAsignado;
        this.fecha = fecha;
        this.hora = hora;
        this.motivoConsulta = motivoConsulta;
        this.estado = estado;
    }

    public int getIdCita() {
        return idCita;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
    public String getNombrePaciente() {
        return nombrePaciente;
    }
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }
    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }
    public String getFechaHora() {
        return fecha;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getFecha() {
        return fecha;
    }
    public String getHora() {
        return hora;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getMotivoConsulta() {
        return motivoConsulta;
    }
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    

    
}
