package models.salarios;

public class Salario_Enfermeros extends Salario {
    

    public Salario_Enfermeros(double salarioBase, double bonus, int pacientesAtendidos) {
        super(salarioBase, bonus);
        
    }

    @Override
    public double calcularSalarioTotal() {
        
        return getSalarioBase()  + getBonus();
    }
    
}
