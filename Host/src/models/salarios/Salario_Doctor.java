package models.salarios;

public class Salario_Doctor extends Salario {
    private int cirugias;

    public Salario_Doctor(double salarioBase, double bonus, int cirugias) {
        super(salarioBase, bonus);
        this.cirugias = cirugias;
    }

    @Override
    public double calcularSalarioTotal() {
        
        return getSalarioBase() + cirugias * 500;
    }
}