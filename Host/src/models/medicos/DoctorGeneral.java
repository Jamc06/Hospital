package models.medicos;

public class DoctorGeneral extends Medico {
    private String especializacion;
    private int capacidadPacientesPorDia;
    private int consultas; // Número de consultas realizadas en el mes
    private double tarifaConsulta;

    public DoctorGeneral(int id, String nombre, String departamento, int yearsExperiencia, double salarioBase,
                         String especializacion, int capacidadPacientes, double tarifaConsulta) {
        super(id, nombre, departamento, yearsExperiencia, salarioBase);
        this.especializacion = especializacion;
        this.capacidadPacientesPorDia = capacidadPacientes;
        this.tarifaConsulta = tarifaConsulta;
        this.consultas = 0;
    }

    public void setConsultas(int consultas) { this.consultas = consultas; }
    public int getConsultas() { return consultas; }
    public String getEspecializacion() { return especializacion; }
    public void setEspecializacion(String especializacion) { this.especializacion = especializacion; }
    public int getCapacidadPacientesPorDia() { return capacidadPacientesPorDia; }
    public void setCapacidadPacientesPorDia(int capacidadPacientesPorDia) { this.capacidadPacientesPorDia = capacidadPacientesPorDia; }

    @Override
    public double calcularSalario() {
        return salarioBase + (consultas * tarifaConsulta);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: DoctorGeneral | Especialización: %s | Consultas mes: %d | Tarifa: %.2f",
                especializacion, consultas, tarifaConsulta);
    }
}
