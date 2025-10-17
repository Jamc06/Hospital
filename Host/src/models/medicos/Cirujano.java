package models.medicos;

public class Cirujano extends Medico {
    private String[] tiposOperaciones;
    private int horasCirugiaDisponibles;
    private double bonoRiesgo;

    public Cirujano(int id, String nombre, String departamento, int anosExp, double salarioBase,
                    String[] tiposOperaciones, int horas, double bonoRiesgo) {
        super(id, nombre, departamento, anosExp, salarioBase);
        this.tiposOperaciones = tiposOperaciones;
        this.horasCirugiaDisponibles = horas;
        this.bonoRiesgo = bonoRiesgo;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + bonoRiesgo + (horasCirugiaDisponibles * 150); 
    }
    
}
