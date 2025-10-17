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
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (consultas * tarifaConsulta);
    }
}