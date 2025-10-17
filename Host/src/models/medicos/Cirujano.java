package models.medicos;

public class Cirujano extends Medico {
    private String[] tiposOperaciones;
    private int horasCirugiaDisponibles;
    private double bonoRiesgo;
    private double tarifaHora;

    public Cirujano(int id, String nombre, String departamento, int anosExp, double salarioBase,
                    String[] tiposOperaciones, int horas, double bonoRiesgo, double tarifaHora) {
        super(id, nombre, departamento, anosExp, salarioBase);
        this.tiposOperaciones = tiposOperaciones;
        this.horasCirugiaDisponibles = horas;
        this.bonoRiesgo = bonoRiesgo;
        this.tarifaHora = tarifaHora;
    }

    public String[] getTiposOperaciones() { return tiposOperaciones; }
    public void setTiposOperaciones(String[] tiposOperaciones) { this.tiposOperaciones = tiposOperaciones; }
    public int getHorasCirugiaDisponibles() { return horasCirugiaDisponibles; }
    public void setHorasCirugiaDisponibles(int horasCirugiaDisponibles) { this.horasCirugiaDisponibles = horasCirugiaDisponibles; }

    @Override
    public double calcularSalario() {
        return salarioBase + bonoRiesgo + (horasCirugiaDisponibles * tarifaHora);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Cirujano | Horas cirugías: %d | Bono riesgo: %.2f",
                horasCirugiaDisponibles, bonoRiesgo);
    }
}
