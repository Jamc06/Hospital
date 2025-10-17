package models.medicos;

public class Radiologo extends Medico {
    private String[] equiposCertificados;
    private double tarifaPorEstudio;

    public Radiologo(int id, String nombre, String departamento, int anosExp, double salarioBase,
                      String[] equiposCertificados, double tarifaPorEstudio) {
        super(id, nombre, departamento, anosExp, salarioBase);
        this.equiposCertificados = equiposCertificados;
        this.tarifaPorEstudio = tarifaPorEstudio;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (equiposCertificados.length * tarifaPorEstudio); 
    }
    
}
