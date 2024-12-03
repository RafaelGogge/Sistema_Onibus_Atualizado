package br.com.unisales;

import java.util.Scanner;

import br.com.unisales.service.AdministradorService;
import br.com.unisales.service.PassageiroService;
import br.com.unisales.service.VendedorService;
import br.com.unisales.table.Administrador;
import br.com.unisales.table.Assento;
import br.com.unisales.table.Onibus;
import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Passagem;
import br.com.unisales.table.Vendedor;

public class Main {
    public static void main(String[] args) {
        VendedorService vservice = new VendedorService();
        PassageiroService psservice = new PassageiroService();
        AdministradorService adservice = new AdministradorService();

        Scanner sc = new Scanner(System.in);

        try {
            boolean continuar = true;
            while (continuar) {
                exibirMenuPrincipal();
                int opcao = sc.nextInt();
                sc.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        menuVendedor(vservice, sc);
                        break;
                    case 2:
                        menuPassageiro(psservice, sc);
                        break;
                    case 3:
                        menuAdministrador(adservice, sc);
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, tente novamente.");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\nSelecione uma das opções abaixo para continuarmos:");
        System.out.println("---------- 1 para Vendedor ----------");
        System.out.println("---------- 2 para Passageiro ----------");
        System.out.println("---------- 3 para Administrador ----------");
        System.out.println("---------- 0 para Sair ----------");
    }

    private static void menuVendedor(VendedorService vservice, Scanner sc) {
        System.out.println("\n---------- MENU VENDEDOR ----------");
        System.out.println("SELECIONE UMA DAS OPÇÕES");
        System.out.println("1 - CADASTRAR PASSAGEIRO");
        System.out.println("2 - VENDER PASSAGEM");
        System.out.println("3 - LISTAR PASSAGEM");

        int opcaoVendedor = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        switch (opcaoVendedor) {
            case 1:
                vservice.cadastrarPassageiro();
                break;
            case 2:
                vservice.venderPassagem(new Passagem());
                break;
            case 3:
                vservice.listarPassagem();
                break;
            default:
                System.out.println("Opção inválida! Por favor, tente novamente.");
        }
    }

    private static void menuPassageiro(PassageiroService psservice, Scanner sc) {
        System.out.println("\n---------- MENU PASSAGEIRO ----------");
        System.out.println("SELECIONE UMA DAS OPÇÕES");
        System.out.println("1 - COMPRAR PASSAGEM");
        System.out.println("2 - LISTAR PASSAGEM");

        int opcaoPassageiro = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        switch (opcaoPassageiro) {
            case 1:
                psservice.comprarPassagem(new Passagem());
                break;
            case 2:
                psservice.listarPassagem(null);
                break;
            default:
                System.out.println("Opção inválida! Por favor, tente novamente.");
        }
    }

    private static void menuAdministrador(AdministradorService adservice, Scanner sc) {
        System.out.println("\n---------- MENU ADMINISTRADOR ----------");
        System.out.println("SELECIONE UMA DAS OPÇÕES");
        System.out.println("1 - CADASTRAR VENDEDOR");
        System.out.println("2 - CADASTRAR PASSAGEIRO");
        System.out.println("3 - CADASTRAR ADMINISTRADOR");
        System.out.println("4 - CADASTRAR ASSENTO");
        System.out.println("5 - CADASTRAR ONIBUS");

        int opcaoAdministrador = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        switch (opcaoAdministrador) {
            case 1:
                adservice.cadastrarVendedor(new Vendedor());
                break;
            case 2:
                adservice.cadastrarPassageiro(new Passageiro());
                break;
            case 3:
                adservice.cadastrarAdministrador(new Administrador());
                break;
            case 4:
                adservice.cadastrarAssento(new Assento());
                break;
            case 5:
                adservice.cadastrarOnibus(new Onibus());
                break;
            default:
                System.out.println("Opção inválida! Por favor, tente novamente.");
        }
    }
}
