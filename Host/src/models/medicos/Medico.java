package models.medicos;

public abstract class Medico {
    protected int idEmpleado;
    protected String nombre;
    protected String departamento;
    protected int yearsExperiencia;
    protected double salarioBase;

    public Medico(int idEmpleado, String nombre, String departamento, int yearsExperiencia, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.departamento = departamento;
        this.yearsExperiencia = yearsExperiencia;
        this.salarioBase = salarioBase;
    }

    public void mostrarInfo() {
        System.out.println(this.toString());
    }

    public abstract double calcularSalario();

    // Getters y setters
    public String getDepartamento() { return departamento; }
    public int getIdEmpleado() { return idEmpleado; }
    public String getNombre() { return nombre; }
    public double getSalarioBase() { return salarioBase; }
    public int getYearsExperiencia() { return yearsExperiencia; }

    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    public void setYearsExperiencia(int yearsExperiencia) { this.yearsExperiencia = yearsExperiencia; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Departamento: %s | Años exp: %d | Salario base: %.2f",
                idEmpleado, nombre, departamento, yearsExperiencia, salarioBase);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Medico)) return false;
        Medico other = (Medico) obj;
        return this.idEmpleado == other.idEmpleado;
    }
}
