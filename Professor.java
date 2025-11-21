import java.util.Objects;

public class Professor {
    private String nome;
    private String matricula;
    private String email;

    public Professor(String nome, String matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Professor professor = (Professor) obj;
        return matricula.equals(professor.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return this.nome + " | Matrícula: " + this.matricula  ;
    }

}
