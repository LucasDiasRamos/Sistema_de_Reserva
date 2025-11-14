import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeReservas {
    private List<Sala> listaDeSalas;
    private List<Professor> listaDeProfessores;
    private List<Reserva> historicoDeReservas;

    public SistemaDeReservas() {
        this.listaDeSalas = new ArrayList<>();
        this.listaDeProfessores = new ArrayList<>();
        this.historicoDeReservas = new ArrayList<>();
    }

    public void adicionarSala(Sala sala) {
        listaDeSalas.add(sala);
        System.out.println("Sala " + sala.getNome() + " cadastrada com sucesso.");
    }

    public void adicionarProfessor(Professor professor) {
        listaDeProfessores.add(professor);
        System.out.println("Professor(a) " + professor.getNome() + " cadastrado(a) com sucesso.");
    }

    public List<Sala> getListaDeSalas() {
        return listaDeSalas;
    }

    public List<Professor> getListaDeProfessores() {
        return listaDeProfessores;
    }

    public List<Reserva> getHistoricoDeReservas() {
        return historicoDeReservas;
    }

    private boolean verificarConflito(Sala sala, LocalDate data, Turno turno) {
        for (Reserva reservaExistente : this.historicoDeReservas) {

            if (reservaExistente.getSala().equals(sala) &&
                    reservaExistente.getData().equals(data) &&
                    reservaExistente.getTurno() == turno) {
                return true;
            }
        }
        return false;
    }

    public boolean fazerReservaUnica(Sala sala, Professor professor, LocalDate data, Turno turno) {
        if (verificarConflito(sala, data, turno)) {
            System.out.println("ERRO: Está sala já esta reservada para essa data e turno.");
            return false;
        }

        Reserva novaReserva = new Reserva(sala, professor, data, turno);
        this.historicoDeReservas.add(novaReserva);
        System.out.println("SUCESSO: Reserva única realizada com sucesso.");
        return true;
    }

    public boolean fazerReservaEmLote(Sala sala, Professor professor, LocalDate dataInicio, LocalDate dataFim,
            Turno turno) {

        List<LocalDate> datasParaReservar = new ArrayList<>();
        
        for (LocalDate data = dataInicio ; !data.isAfter(dataFim); data = data.plusDays(1)){
            if (data.getDayOfWeek() == DayOfWeek.SUNDAY) {
                continue;
            }

            if (verificarConflito(sala, data, turno)) {
              System.out.println("ERRO: Conflito Encontrado para o dia " + data);
              System.out.println("Nenhuma reserva foi realizada.");
              return false; 
            }
            datasParaReservar.add(data);
        }

        for (LocalDate data : datasParaReservar) {
            Reserva novaReserva = new Reserva(sala, professor, data, turno);
            this.historicoDeReservas.add(novaReserva);
        }

        System.out.println("SUCESSO: Reserva de " + dataInicio + "até " + dataFim + " realizada com sucesso.");
        return true;
    }

    

}
