import java.time.LocalDate;

public class Reserva {
    private Sala sala;
    private Professor professor;
    private LocalDate data;
    private Turno turno;

    public Reserva(Sala sala, Professor professor, LocalDate data, Turno turno) {
        this.sala = sala;
        this.professor = professor;
        this.data = data;
        this.turno = turno;
    }

    public Sala getSala() {
        return sala;
    }

    public Professor getProfessor() {
        return professor;
    }

    public LocalDate getData() {
        return data;
    }

    public Turno getTurno() {
        return turno;
    }
}
