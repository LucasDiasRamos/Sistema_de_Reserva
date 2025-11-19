import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final SistemaDeReservas sistemaDeReservas = new SistemaDeReservas();

    public static void main(String[] args) {

        int opcao;
        do {
            Menu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProfessor();
                    break;
                case 2:
                    cadastrarSala();
                    break;
                case 3:
                    fazerReserva();
                    break;
                case 4:
                    listarSalas();
                    break;
                case 5:
                    listarProfessores();
                    break;
                case 6:
                    listarReservas();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void Menu() {
        System.out.println("\n --- SISTEMA DE RESERVA DE SALA ---");
        System.out.println("1 - Cadastrar professor");
        System.out.println("2 - Cadastrar sala");
        System.out.println("3 - Fazer Reserva");
        System.out.println("4 - Listar Todas as Salas");
        System.out.println("5 - Listar Todos os Professores");
        System.out.println("6 - Listar Histórico de Reservas");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProfessor() {
        System.out.println("\n--- Cadastrar Professor ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        Professor professor = new Professor(nome, email, matricula);
        sistemaDeReservas.adicionarProfessor(professor);
    }

    private static void cadastrarSala() {
        System.out.println("\n--- Cadastrar Sala ---");

        System.out.print("Selecione o tipo da sala (1 - Laboratório, 2 - Sala de Aula): ");
        int tipoSala = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome da Sala: ");
        String Nome = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Número da Sala: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Capacidade da Sala: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Localização (Exemplo: Bloco A ): ");
        String bloco = scanner.nextLine();

        if (tipoSala == 1) {
            System.out.println("Informe a quantidade de computadores: ");
            int quantidadeComputadores = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Informe o Software Disponível (Ex: CAD ): ");
            String softwareDisponivel = scanner.nextLine();

            Laboratorio laboratorio = new Laboratorio(Nome, numero, capacidade, bloco, quantidadeComputadores,
                    softwareDisponivel);
        }
        

        else if (tipoSala == 2) {
            SalaDeAula salaDeAula = new SalaDeAula(Nome, numero, capacidade, bloco);
            sistemaDeReservas.adicionarSala(salaDeAula);
        }

        else {
            System.out.println("Tipo de sala inválido. Cadastro cancelado.");

        }
    }

    private static void fazerReserva() {
        System.out.println("\n --- Fazer Reserva ---");
        if (sistemaDeReservas.getListaDeProfessores().isEmpty()) {
            System.out.println("ERRO: Nenhum professor Cadastrado no sistema.");
            return;
        }
        if (sistemaDeReservas.getListaDeSalas().isEmpty()) {
            System.out.println("ERRO: Nenhuma sala Cadastrada no sistema.");
        }

        Professor professor = selecionarProfessor();
        Sala sala = selecionarSala();
        Turno turno = selecionarTurno();

        System.out.println("Tipo de Reserva (1 - Única, 2 - Periodo): ");
        int tipoReserva = scanner.nextInt();
        scanner.nextLine();

        if (tipoReserva == 1) {

            LocalDate data = lerData("Digite a data da Reserva (dd/mm/aaaa): ");
            sistemaDeReservas.fazerReservaUnica(sala, professor, data, turno);

        } else if (tipoReserva == 2) {
            LocalDate dataInicio = lerData("Digite a data de Início da Reserva (dd/mm/aaaa): ");
            LocalDate dataFim = lerData("Digite a data de Fim da Reserva (dd/mm/aaaa): ");

            if (dataFim.isBefore(dataInicio)) {
                System.out.println("ERRO: A data de fim não pode ser anterior à data de início.");
                return;
            }

            sistemaDeReservas.fazerReservaEmLote(sala, professor, dataInicio, dataFim, turno);
        } else {
            System.out.println("Tipo de reserva inválido. Reserva cancelada.");
        }

    }

    private static void listarSalas() {
        System.out.println("\n--- Lista de Salas Cadastradas ---");
        List<Sala> salas = sistemaDeReservas.getListaDeSalas();
        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
        }
        for (Sala sala : salas) {
            System.out.println(sala.getDescricao());

        }
    }

    private static void listarProfessores() {
        System.out.println("\n--- Lista de Professores Cadastrados ---");
        List<Professor> professores = sistemaDeReservas.getListaDeProfessores();
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        }
        for (Professor professor : professores) {
            System.out.println(professor.toString());

        }
    }

    private static void listarReservas() {
        System.out.println("\n--- Histórico de Reservas ---");
        List<Reserva> reservas = sistemaDeReservas.getHistoricoDeReservas();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva realizada.");
            return;
        }

        reservas.sort((r1, r2) -> r1.getData().compareTo(r2.getData()));

        for (Reserva reserva : reservas) {
            System.out.printf("Data: %s | Sala: %s | Professor: %s | Turno: %s\n",
                    reserva.getData().format(dateFormatter),
                    reserva.getSala().getNome(),
                    reserva.getProfessor().getNome(),
                    reserva.getTurno().toString());

        }
    }

    private static Professor selecionarProfessor() {
        List<Professor> professores = sistemaDeReservas.getListaDeProfessores();
        while (true) {
            System.out.println("\n--- Selecione o Professor ---");
            for (int i = 0; i < professores.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), professores.get(i).toString());
            }
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao > 0 && opcao <= professores.size()) {
                    return professores.get(opcao - 1);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    private static Sala selecionarSala() {
        List<Sala> salas = sistemaDeReservas.getListaDeSalas();
        while (true) {
            System.out.println("\n--- Selecione a Sala ---");
            for (int i = 0; i < salas.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), salas.get(i).toString());
            }
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao > 0 && opcao <= salas.size()) {
                    return salas.get(opcao - 1);
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    private static Turno selecionarTurno() {
        while (true) {
            System.out.println("\n--- Selecione o Turno ---");

            for (Turno turno : Turno.values()) {
                System.out.printf("%d. %s\n", (turno.ordinal() + 1), turno.getDescricao());
            }

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao > 0 && opcao <= Turno.values().length) {
                    return Turno.values()[opcao - 1];
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String textoData = scanner.nextLine();
            try {
                return LocalDate.parse(textoData, dateFormatter);
            } catch (DateTimeException e) {
                System.out.println("Formato de data inválido. Tente novamente usando dd/mm/aaaa.");
            }
        }
    }

}
