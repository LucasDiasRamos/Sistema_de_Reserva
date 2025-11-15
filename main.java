import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final SistemaDeReservas sistemaDeReservas = new SistemaDeReservas();

    public static void main(String[] args) {

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

        if (tipoSala == 1 ) {
            System.out.println("Informe a quantidade de computadores: ");
            int quantidadeComputadores = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Informe o Software Disponível (Ex: CAD ): ");
            String softwareDisponivel = scanner.nextLine();

            Laboratorio laboratorio = new Laboratorio(Nome, numero, capacidade, bloco, quantidadeComputadores, softwareDisponivel);
        }

        else if (tipoSala == 2) {
            SalaDeAula salaDeAula = new SalaDeAula (Nome, numero, capacidade, bloco);
            sistemaDeReservas.adicionarSala(salaDeAula);
        } 
        
        else {
            System.out.println("Tipo de sala inválido. Cadastro cancelado.");

        }
    }

    
}
