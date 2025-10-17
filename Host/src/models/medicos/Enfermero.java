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

    @Override
    public double calcularSalario() {
        double extra = tipoTurno.equalsIgnoreCase("nocturno") ? 100 : 0;
        return salarioBase + extra;
    }
    public void setNivelCertificacion(String nivelCertificacion) {
        this.nivelCertificacion = nivelCertificacion;
    }
    public String getNivelCertificacion() {
        return nivelCertificacion;
    }
    
}
