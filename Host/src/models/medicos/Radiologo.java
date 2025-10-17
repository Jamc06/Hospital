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

    public String[] getEquiposCertificados() { return equiposCertificados; }
    public void setEquiposCertificados(String[] equiposCertificados) { this.equiposCertificados = equiposCertificados; }
    public double getTarifaPorEstudio() { return tarifaPorEstudio; }
    public void setTarifaPorEstudio(double tarifaPorEstudio) { this.tarifaPorEstudio = tarifaPorEstudio; }

    @Override
    public double calcularSalario() {
        return salarioBase + (equiposCertificados != null ? equiposCertificados.length * tarifaPorEstudio : 0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tipo: Radiologo | Equipos: %d | Tarifa estudio: %.2f",
                (equiposCertificados != null ? equiposCertificados.length : 0), tarifaPorEstudio);
    }
}