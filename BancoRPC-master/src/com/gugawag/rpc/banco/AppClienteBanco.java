package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry("192.168.0.26",1099);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    //chamada ao método remoto, como se fosse executar localmente
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                	Scanner novaConta = new Scanner(System.in);
                	System.out.print("Número da conta: ");
                	String nrConta = novaConta.nextLine();
                	
                	System.out.print("Depósito inicial: ");
                    Double valor = novaConta.nextDouble();
                    
                	System.out.println(banco.criarConta(nrConta, valor));
                	break;
                }
                case 4: {
                	Scanner adicionarConta = new Scanner(System.in);
                	System.out.print("Número da conta: ");
                	String nrConta = adicionarConta.nextLine();
                	
                	System.out.print("Depósito inicial: ");
                    Double valor = adicionarConta.nextDouble();
                    
                	System.out.println(banco.adicionarConta(nrConta, valor));
                	break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
    	System.out.println("\nKEVEN ALLAN NASCIMENTO DA SILVA");
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Criar conta");
        System.out.println("4 - Adicionar conta");
        System.out.println("9 - Sair");
    }

}
