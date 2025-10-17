package controller;

import models.citas.Cita;
import models.citas.EstadoCita;
import models.medicos.Medico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class controlador {
    private List<Medico> medicos;
    private List<Cita> citas;
    private int siguienteIdCita = 1;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public controlador() {
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
        iniciarSistema();
    }

    public void iniciarSistema() {
        System.out.println("Sistema del Hospital iniciado.");
    }

    public void detenerSistema() {
        System.out.println("Sistema del Hospital detenido.");
    }

    // Registrar personal médico
    public void agregarMedico(Medico m) {
        medicos.add(m);
        System.out.println("Medico agregado: " + m.getNombre());
    }

    // Listar medicos
    public List<Medico> listarMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    // Crear y agendar cita simple (si hay conflicto, devuelve false)
    public Cita agendarCita(String nombrePaciente, Medico medico, LocalDateTime fechaHora,
                            String tipoCita) {
        if (!estaDisponible(medico, fechaHora)) {
            // intentar reagendar automáticamente (buscar próximo slot)
            LocalDateTime nuevo = buscarSiguienteHorarioDisponible(medico, fechaHora);
            if (nuevo != null) {
                int id = siguienteIdCita++;
                Cita c = new Cita(id, nombrePaciente, medico, nuevo, tipoCita, EstadoCita.REAGENDADA);
                c.getHistorialReagendamientos().add("Auto-reagendada a creación: " + nuevo.format(fmt));
                citas.add(c);
                notificar("Cita reagendada automáticamente para " + nombrePaciente + " a " + nuevo.format(fmt));
                return c;
            } else {
                System.out.println("No hay horario disponible para " + medico.getNombre());
                return null;
            }
        } else {
            int id = siguienteIdCita++;
            Cita c = new Cita(id, nombrePaciente, medico, fechaHora, tipoCita, EstadoCita.PROGRAMADA);
            citas.add(c);
            System.out.println("Cita agendada: " + c);
            return c;
        }
    }

    // Verifica si el medico tiene otra cita en la misma fecha/hora
    public boolean estaDisponible(Medico medico, LocalDateTime fechaHora) {
        for (Cita c : citas) {
            if (c.getMedicoAsignado().equals(medico) && c.getFechaHora().equals(fechaHora)
                    && c.getEstado() != EstadoCita.CANCELADA && c.getEstado() != EstadoCita.COMPLETADA) {
                return false;
            }
        }
        return true;
    }

    // Buscar siguiente horario disponible simple (suma 30 minutos hasta 8 horas en adelante)
    public LocalDateTime buscarSiguienteHorarioDisponible(Medico medico, LocalDateTime desde) {
        LocalDateTime intento = desde.plusMinutes(30);
        LocalDateTime limite = desde.plusHours(8);
        while (!intento.isAfter(limite)) {
            if (estaDisponible(medico, intento)) return intento;
            intento = intento.plusMinutes(30);
        }
        return null;
    }

    // reasignar cita (manual), con historial
    public boolean reasignarCita(int idCita, Medico nuevoMedico, LocalDateTime nuevaFechaHora) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                // comprobar disponibilidad
                if (!estaDisponible(nuevoMedico, nuevaFechaHora)) {
                    System.out.println("El medico no está disponible en esa fecha/hora.");
                    return false;
                }
                c.getHistorialReagendamientos().add("Reasignado medico: " + c.getMedicoAsignado().getNombre()
                        + " -> " + nuevoMedico.getNombre());
                c.setMedicoAsignado(nuevoMedico);
                c.setFechaHora(nuevaFechaHora);
                c.setEstado(EstadoCita.REAGENDADA);
                notificar("Cita " + idCita + " reasignada a " + nuevoMedico.getNombre() + " " + nuevaFechaHora.format(fmt));
                return true;
            }
        }
        System.out.println("Cita no encontrada.");
        return false;
    }

    // cancelar
    public boolean cancelarCita(int idCita) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                c.setEstado(EstadoCita.CANCELADA);
                notificar("Cita " + idCita + " cancelada.");
                return true;
            }
        }
        return false;
    }

    // marcar como completada
    public boolean completarCita(int idCita) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                c.setEstado(EstadoCita.COMPLETADA);
                notificar("Cita " + idCita + " completada.");
                return true;
            }
        }
        return false;
    }

    // Notificación simulada (consola)
    private void notificar(String mensaje) {
        System.out.println("[NOTIFICACIÓN] " + mensaje);
    }

    // Reportes
    public void reporteNominaPorDepartamento() {
        Map<String, Double> totales = new HashMap<>();
        for (Medico m : medicos) {
            double sal = m.calcularSalario();
            totales.put(m.getDepartamento(), totales.getOrDefault(m.getDepartamento(), 0.0) + sal);
        }
        System.out.println(" Nómina por departamento ");
        for (String dept : totales.keySet()) {
            System.out.printf("%s -> Total: %.2f%n", dept, totales.get(dept));
        }
    }

    public void reportePersonal() {
        System.out.println(" Reporte de Personal ");
        for (Medico m : medicos) {
            System.out.println(m.toString());
        }
    }

    public void reporteCitasPorEstado() {
        System.out.println(" Reporte de Citas por Estado ");
        Map<String, List<Cita>> mapa = new HashMap<>();
        for (Cita c : citas) {
            mapa.computeIfAbsent(c.getEstado().name(), k -> new ArrayList<>()).add(c);
        }
        for (String estado : mapa.keySet()) {
            System.out.println("- " + estado + " (" + mapa.get(estado).size() + ")");
            for (Cita c : mapa.get(estado)) {
                System.out.println("   " + c.toString());
            }
        }
    }

    public void reporteHistorialReagendamientos(int idCita) {
        for (Cita c : citas) {
            if (c.getIdCita() == idCita) {
                System.out.println("Historial de cita " + idCita);
                c.getHistorialReagendamientos().forEach(System.out::println);
                return;
            }
        }
        System.out.println("Cita no encontrada.");
    }

    // buscar cita por id
    public Cita obtenerCitaPorId(int id) {
        for (Cita c : citas) if (c.getIdCita() == id) return c;
        return null;
    }

    public List<Cita> listarCitas() {
        return Collections.unmodifiableList(citas);
    }

    // Asignación inteligente: busca un medico disponible con la especializacion (simple match por clase o departamento)
    public Cita asignarCitaInteligente(String nombrePaciente, String tipoCita, String departamentoPreferido, LocalDateTime fechaDeseada) {
        // prioriza medicos en departamentoPreferido
        List<Medico> candidatos = new ArrayList<>();
        for (Medico m : medicos) {
            if (departamentoPreferido == null || m.getDepartamento().equalsIgnoreCase(departamentoPreferido)) {
                candidatos.add(m);
            }
        }
        // si no hay candidatos por departamento, tomar todos
        if (candidatos.isEmpty()) candidatos.addAll(medicos);

        // buscar primer candidato disponible en la fecha
        for (Medico m : candidatos) {
            if (estaDisponible(m, fechaDeseada)) {
                return agendarCita(nombrePaciente, m, fechaDeseada, tipoCita);
            }
        }
        // si no hay nadie en esa hora, intentar auto-reagendamiento usando el primer candidato
        if (!candidatos.isEmpty()) {
            Medico prim = candidatos.get(0);
            LocalDateTime nuevo = buscarSiguienteHorarioDisponible(prim, fechaDeseada);
            if (nuevo != null) {
                return agendarCita(nombrePaciente, prim, nuevo, tipoCita);
            }
        }
        System.out.println("No se pudo asignar la cita inteligentemente.");
        return null;
    }
}
