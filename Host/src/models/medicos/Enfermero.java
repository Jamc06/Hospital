package models.medicos;

public class Enfermero extends Medico {
    private String tipoTurno; // diurno/nocturno
    private String nivelCertificacion;

    public Enfermero(int id, String nombre, String departamento, int yearsExperiencia, double salarioBase,
                     String tipoTurno, String nivelCertificacion) {
        super(id, nombre, departamento, yearsExperiencia, salarioBase);
        this.tipoTurno = tipoTurno;
        this.nivelCertificacion = nivelCertificacion;
    }

    public String getTipoTurno() { return tipoTurno; }
    public void setTipoTurno(String tipoTurno) { this.tipoTurno = tipoTurno; }
    public void setNivelCertificacion(String nivelCertificacion) { this.nivelCertificacion = nivelCertificacion; }
    public String getNivelCertificacion() { return nivelCertificacion; }

    @Override
    public double calcularSalario() {
        double extra = tipoTurno != null && tipoTurno.equalsIgnoreCase("nocturno") ? 100 : 0;
        return salarioBase + extra;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Enfermero | Turno: %s | Certificación: %s",
                tipoTurno, nivelCertificacion);
    }
}
