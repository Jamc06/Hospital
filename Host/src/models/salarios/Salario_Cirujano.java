package models.salarios;

public class Salario_Cirujano extends Salario {
    private int cirugias;

    public Salario_Cirujano(double salarioBase, double bonus, int cirugias) {
        super(salarioBase, bonus);
        this.cirugias = cirugias;
    }

    @Override
    public double calcularSalarioTotal() {
        
        return getSalarioBase() + cirugias * 1000+ getBonus();
    }
    
}
