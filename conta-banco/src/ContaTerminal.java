import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ContaTerminal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(localeBR);
        DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("Banco Zem: onde seu futuro financeiro começa agora. Sejam bem-vindos!\n");

        // 1. Solicita dados da conta
        System.out.print("Digite o número da Conta: ");
        int numeroConta = lerInt(scanner);
        scanner.nextLine(); // limpa o \n pendente

        System.out.print("Digite o número da Agência: ");
        String agencia = scanner.nextLine().trim();

        System.out.print("Digite o nome do Cliente: ");
        String nomeCliente = scanner.nextLine().trim();

        System.out.print("Digite o saldo inicial: ");
        double saldo = lerDouble(scanner);
        scanner.nextLine(); // limpa o \n pendente

        // 6. Criar senha
        System.out.print("Crie uma senha: ");
        String senha = scanner.nextLine().trim();

        System.out.print("Confirme sua senha para acessar: ");
        String senhaConfirmada = scanner.nextLine().trim();

        if (!senha.equals(senhaConfirmada)) {
            System.out.println("Senha incorreta. Acesso negado.");
            scanner.close();
            return;
        }

        // Confirmação da criação
        System.out.println("\n Conta criada com sucesso em " + LocalDateTime.now().format(formatoDataHora));
        System.out.printf("Olá, %s. Obrigado por criar uma conta em nosso banco!%n", nomeCliente.toUpperCase());
        System.out.printf("Agência: %s | Conta: %d | Saldo: %s%n", agencia, numeroConta, formatoMoeda.format(saldo));

        int opcao;
        do {
            System.out.println("\n===== MENU DE OPERAÇÕES =====");
            System.out.println("1 - Sacar");
            System.out.println("2 - Investir");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInt(scanner);
            scanner.nextLine(); // limpa o \n pendente

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para saque: ");
                    double saque = lerDouble(scanner);
                    scanner.nextLine(); // limpa o \n pendente
                    if (saque > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= saque;
                        System.out.println("Saque realizado com sucesso.");
                        System.out.println("Saldo atual: " + formatoMoeda.format(saldo));
                    }
                    break;

                case 2:
                    System.out.print("Digite o valor para investir: ");
                    double investimento = lerDouble(scanner);
                    scanner.nextLine(); // limpa o \n pendente
                    if (investimento > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        saldo -= investimento;
                        double rendimento = investimento * 0.005;
                        saldo += rendimento;
                        System.out.println("Investimento realizado com rendimento de 0.5%");
                        System.out.println("Rendimento: " + formatoMoeda.format(rendimento));
                        System.out.println("Saldo atual: " + formatoMoeda.format(saldo));
                    }
                    break;

                case 3:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 3);

        System.out.println("\nOperação finalizada em: " + LocalDateTime.now().format(formatoDataHora));
        scanner.close();
    }

    // Métodos auxiliares de validação
    private static int lerInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número inteiro válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double lerDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Digite um valor decimal válido: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}

