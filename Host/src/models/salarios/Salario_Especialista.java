package models.salarios;

public class Salario_Especialista extends Salario {
    

    public Salario_Especialista(double salarioBase, double bonus, int pacientesAtendidos) {
        super(salarioBase, bonus);

    }

    @Override
    public double calcularSalarioTotal() {
        
        return getSalarioBase()  + getBonus();
    }
    
}
