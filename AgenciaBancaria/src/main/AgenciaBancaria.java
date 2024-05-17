package main;

import java.util.ArrayList;
import java.util.Scanner;
import model.Pessoa;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

public class AgenciaBancaria {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("-------------Bem vindos a nossa Agência---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   Opção 1 - Criar conta          |");
        System.out.println("|   Opção 2 - Depositar            |");
        System.out.println("|   Opção 3 - Sacar                |");
        System.out.println("|   Opção 4 - Transferir           |");
        System.out.println("|   Opção 5 - Pix                  |");
        System.out.println("|   Opção 6 - Listar               |");
        System.out.println("|   Opção 7 - Sair                 |");
        System.out.println("------------------------------------------------------");       

        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;

            case 2:
                depositar();
                break;

            case 3:
                sacar();
                break;

            case 4:
                transferir();
                break;

            case 5:
                transferenciaViaPix();
                break;

            case 6:
                listarOpcoes();
                break;

            case 7:
                System.out.println("Flw é nóis!");
                System.exit(0); // para o sistema

            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void listarOpcoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma opção para listar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   Opção 1 - Todas as Contas                 |");
        System.out.println("|   Opção 2 - Somente Contas Correntes        |");
        System.out.println("|   Opção 3 - Somente Contas Poupança         |");
        System.out.println("|   Opção 4 - Voltar ao Menu Principal         |");
        System.out.println("------------------------------------------------------");

        int opcao = input.nextInt();

        switch (opcao) {
            case 1:
                listarContas();
                break;

            case 2:
                listarContasCorrentes();
                break;

            case 3:
                listarContasPoupanca();
                break;

            case 4:
                operacoes();
                break;

            default:
                System.out.println("Opção inválida!");
                listarOpcoes();
                break;
        }
    }

    public static void criarConta() {
        System.out.println("\nNome: ");
        String nome = input.next();

        System.out.println("\nCPF: ");
        String cpf = input.next();

        System.out.println("Email: ");
        String email = input.next();
        
        System.out.println("Renda Anual: ");
        double rendaAnual = input.nextDouble();

        System.out.println("Tipo de conta (1 para Corrente, 2 para Poupança): ");
        int tipoConta = input.nextInt();

        Pessoa cliente = new Pessoa(nome, cpf, email);

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido!");
            return;
        }

        contasBancarias.add(conta);
        System.out.println("--- Sua conta foi criada com sucesso! ---");

        operacoes();
    }

    private static Conta encontrarContaPorNumero(int numeroConta) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta contaa : contasBancarias) {
                if (contaa.getNumeroConta() == numeroConta) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }

    private static Conta encontrarContaPorCpf(String cpf) {
        Conta conta = null;
        if (contasBancarias.size() > 0) {
            for (Conta contaa : contasBancarias) {
                if (contaa.getClient().getCpf().equals(cpf)) {
                    conta = contaa;
                }
            }
        }
        return conta;
    }

    public static void depositar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();
        Conta conta = encontrarContaPorNumero(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja depositar? ");
            Double valorDeposito = input.nextDouble();

            conta.depositar(valorDeposito);
        } else {
            System.out.println("--- Conta não encontrada ---");
        }

        operacoes();
    }

    public static void sacar() {
        System.out.println("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarContaPorNumero(numeroConta);

        if (conta != null) {
            System.out.println("Qual valor deseja sacar? ");
            Double valorSaque = input.nextDouble();

            conta.sacar(valorSaque);
            System.out.println("--- Saque realizado com sucesso! ---");
        } else {
            System.out.println("--- Conta não encontrada ---");
        }

        operacoes();
    }

    public static void transferenciaViaPix() {
        System.out.println("CPF do destinatário: ");
        String cpfDestinatarioStr = input.next();

        System.out.println("Valor da transferência: ");
        Double valorTransferencia = input.nextDouble();

        System.out.println("Número da conta de origem: ");
        int numeroContaOrigem = input.nextInt();
        Conta contaOrigem = encontrarContaPorNumero(numeroContaOrigem);

        if (contaOrigem != null) {
            Conta cpfDestinatario = encontrarContaPorCpf(cpfDestinatarioStr);
            
            if (cpfDestinatario != null) {
                contaOrigem.transferenciaViaPix(cpfDestinatario, valorTransferencia);
            } else {
                System.out.println("Conta do destinatário não encontrada.");
            }
        } else {
            System.out.println("Conta de origem não encontrada.");
        }

        operacoes();
    }

    public static void transferir() {
        System.out.println("Número da conta que vai enviar a transferência: ");
        int numeroContaRemetente = input.nextInt();

        Conta contaRemetente = encontrarContaPorNumero(numeroContaRemetente);

        if (contaRemetente != null) {
            System.out.println("Número da conta do destinatário: ");
            int numeroContaDestinatario = input.nextInt();

            Conta contaDestinatario = encontrarContaPorNumero(numeroContaDestinatario);

            if (contaDestinatario != null) {
                System.out.println("Valor da transferência: ");
                Double valor = input.nextDouble();

                contaRemetente.transferencia(contaDestinatario, valor);

            } else {
                System.out.println("--- A conta para depósito não foi encontrada ---");
            }

        } else {
            System.out.println("--- Conta para transferência não encontrada ---");
        }
        operacoes();
    }

    public static void listarContasPoupanca() {
        if (contasBancarias.size() > 0) {
            System.out.println("----- Contas Poupança -----");
            for (Conta conta : contasBancarias) {
                if (conta instanceof ContaPoupanca) {
                    System.out.println(conta);
                }
            }
        } else {
            System.out.println("--- Não há contas poupança cadastradas ---");
        }

        operacoes();
    }
    
    public static void listarContasCorrentes() {
        if (contasBancarias.size() > 0) {
            System.out.println("----- Contas Correntes -----");
            for (Conta conta : contasBancarias) {
                if (conta instanceof ContaCorrente) {
                    System.out.println(conta);
                }
            }
        } else {
            System.out.println("--- Não há contas correntes cadastradas ---");
        }

        operacoes();
    }
    
    public static void listarContas() {
        if (contasBancarias.size() > 0) {
            for (Conta conta : contasBancarias) {
                System.out.println(conta);
            }
        } else {
            System.out.println("--- Não há contas cadastradas ---");
        }

        operacoes();
    }
}
