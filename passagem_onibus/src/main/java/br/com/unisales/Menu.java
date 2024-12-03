package br.com.unisales;

import java.util.Scanner;

import br.com.unisales.service.AdministradorService;
import br.com.unisales.service.PassageiroService;
import br.com.unisales.service.VendedorService;
import br.com.unisales.table.Administrador; 
import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Passagem;
import br.com.unisales.table.Vendedor;
    
public class Menu {
    public static void main(String[] args) {

        VendedorService vservice = new VendedorService();
        PassageiroService psservice = new PassageiroService();
        AdministradorService adservice = new AdministradorService();

        Scanner sc = new Scanner(System.in);

        try {
            boolean continuar = true;
            while (continuar) {
                System.out.print("Selecione uma das opções abaixo para continuarmos:\n");
                System.out.println("---------- 1 para Vendedor ----------\n---------- 2 para Passageiro ----------\n---------- 3 para Administrador ----------\n---------- 0 para Sair ----------");

                int opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("---------- MENU VENDEDOR ----------\nSELECIONE UMA DAS OPÇÕES");
                        System.out.println("1 - CADASTRAR PASSAGEIRO\n2 - VENDER PASSAGEM\n3 - LISTAR PASSAGEM");
                        int opcaoVendedor = sc.nextInt();
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
                                System.out.println("Opção inválida!");
                            }
                        break;
                    case 2:
                        System.out.println("---------- MENU PASSAGEIRO ----------\nSELECIONE UMA DAS OPÇÕES");
                        System.out.println("1 - COMPRAR PASSAGEM\n2 - LISTAR PASSAGEM");
                        int opcaoPassageiro = sc.nextInt();
                        switch (opcaoPassageiro) {
                            case 1:
                                psservice.comprarPassagem(new Passagem());
                                break;
                            case 2:
                                psservice.listarPassagem(null);
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                        break;
                    case 3:
                        System.out.println("---------- MENU ADMINISTRADOR ----------\nSELECIONE UMA DAS OPÇÕES");
                        System.out.println("1 - CADASTRAR VENDEDOR\n2 - CADASTRAR PASSAGEIRO\n3 - LISTAR PASSAGEM\n4 - CADASTRAR ONIBUS");
                        int opcaoAdministrador = sc.nextInt();
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
                                adservice.cadastrarOnibus();
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
