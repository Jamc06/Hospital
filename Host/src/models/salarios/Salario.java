package models.salarios;


public abstract class Salario {
    private double salarioBase;
    private double bonus;
    private double salariofinal;
    private double tarifa;


    public Salario(double salarioBase, double bonus) {
        this.salarioBase = salarioBase;
        this.bonus = bonus;  

    }

    public abstract double calcularSalarioTotal();

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
