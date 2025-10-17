package vista;

import controller.controlador;
import models.citas.Cita;
import models.citas.EstadoCita;
import models.medicos.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        controlador manager = new controlador();

        // Datos de prueba 
        manager.agregarMedico(new DoctorGeneral(1, "Dr. Carlos Lopez", "Medicina General", 10, 1200.0, "Medicina interna", 20, 15.0));
        manager.agregarMedico(new DoctorGeneral(5, "Dr. Merida Kempez", "Medicina General", 10, 1200.0, "Medicina interna", 20, 15.0));
        manager.agregarMedico(new Cirujano(2, "Dra. Ana Perez", "Cirugía", 12, 1800.0, new String[]{"Apendicectomía", "Cesárea"}, 5, 500.0, 150.0));
        manager.agregarMedico(new Enfermero(3, "Enf. Maria Torres", "Enfermería", 5, 600.0, "diurno", "Nivel 2"));
        manager.agregarMedico(new Radiologo(4, "Dr. Juan Morales", "Radiología", 8, 1400.0, new String[]{"RX", "Ultrasonido"}, 20.0));

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int op = 0;
        do {
            System.out.println("\n--- Hospital Menu ---");
            System.out.println("1. Agendar cita (manual)");
            System.out.println("2. Asignación inteligente");
            System.out.println("3. Listar medicos");
            System.out.println("4. Listar citas");
            System.out.println("5. Reagendar / reasignar cita");
            System.out.println("6. Cancelar cita");
            System.out.println("7. Completar cita");
            System.out.println("8. Reporte: Nómina por departamento");
            System.out.println("9. Reporte: Personal");
            System.out.println("10. Reporte: Citas por estado");
            System.out.println("11. Historial de reagendamientos de una cita");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) { op = -1; }

            switch (op) {
                case 1:
                    System.out.print("Nombre paciente: ");
                    String paciente = scanner.nextLine();
                    System.out.print("ID medico: ");
                    int idMed = Integer.parseInt(scanner.nextLine());
                    System.out.print("Fecha y hora (YYYY-MM-DD HH:mm): ");
                    String fh = scanner.nextLine();
                    System.out.print("Tipo de cita: ");
                    String tipo = scanner.nextLine();
                    try {
                        LocalDateTime fechaHora = LocalDateTime.parse(fh, fmt);
                        // buscar medico
                        Medico medSeleccionado = null;
                        for (Medico m : manager.listarMedicos()) if (m.getIdEmpleado() == idMed) medSeleccionado = m;
                        if (medSeleccionado == null) {
                            System.out.println("Medico no encontrado.");
                        } else {
                            manager.agendarCita(paciente, medSeleccionado, fechaHora, tipo);
                        }
                    } catch (Exception ex) {
                        System.out.println("Formato de fecha inválido.");
                    }
                    break;
                case 2:
                    System.out.print("Nombre paciente: ");
                    String pac2 = scanner.nextLine();
                    System.out.print("Tipo de cita: ");
                    String tipoC = scanner.nextLine();
                    System.out.print("Departamento preferido (o ENTER para cualquiera): ");
                    String dept = scanner.nextLine();
                    if (dept.trim().isEmpty()) dept = null;
                    System.out.print("Fecha y hora deseada (YYYY-MM-DD HH:mm): ");
                    String fh2 = scanner.nextLine();
                    try {
                        LocalDateTime fechaDeseada = LocalDateTime.parse(fh2, fmt);
                        Cita c = manager.asignarCitaInteligente(pac2, tipoC, dept, fechaDeseada);
                        if (c != null) System.out.println("Asignación inteligente resultó en: " + c);
                    } catch (Exception ex) {
                        System.out.println("Formato de fecha inválido.");
                    }
                    break;
                case 3:
                    List<Medico> lista = manager.listarMedicos();
                    System.out.println(" Médicos ");
                    for (Medico m : lista) System.out.println(m.toString());
                    break;
                case 4:
                    List<Cita> citas = manager.listarCitas();
                    System.out.println(" Citas ");
                    for (Cita c : citas) System.out.println(c.toString());
                    break;
                case 5:
                    System.out.print("ID cita a reasignar/reagendar: ");
                    int idc = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nuevo ID de médico: ");
                    int idn = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nueva fecha y hora (YYYY-MM-DD HH:mm): ");
                    String nf = scanner.nextLine();
                    try {
                        LocalDateTime nFecha = LocalDateTime.parse(nf, fmt);
                        Medico nuevoMed = null;
                        for (Medico m : manager.listarMedicos()) if (m.getIdEmpleado() == idn) nuevoMed = m;
                        if (nuevoMed == null) {
                            System.out.println("Medico no encontrado.");
                        } else {
                            manager.reasignarCita(idc, nuevoMed, nFecha);
                        }
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido.");
                    }
                    break;
                case 6:
                    System.out.print("ID cita a cancelar: ");
                    int idCan = Integer.parseInt(scanner.nextLine());
                    if (manager.cancelarCita(idCan)) System.out.println("Cancelada.");
                    else System.out.println("No encontrada.");
                    break;
                case 7:
                    System.out.print("ID cita a completar: ");
                    int idComp = Integer.parseInt(scanner.nextLine());
                    if (manager.completarCita(idComp)) System.out.println("Completada.");
                    else System.out.println("No encontrada.");
                    break;
                case 8:
                    manager.reporteNominaPorDepartamento();
                    break;
                case 9:
                    manager.reportePersonal();
                    break;
                case 10:
                    manager.reporteCitasPorEstado();
                    break;
                case 11:
                    System.out.print("ID cita: ");
                    int idHist = Integer.parseInt(scanner.nextLine());
                    manager.reporteHistorialReagendamientos(idHist);
                    break;
                case 12:
                    System.out.println("Saliendo...");
                    manager.detenerSistema();
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (op != 12);

        scanner.close();
    }
}
