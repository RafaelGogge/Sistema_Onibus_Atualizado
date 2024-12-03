package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.dao.VendedorDao;
import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Passagem;
import br.com.unisales.table.Vendedor;

public class VendedorService {

    private final VendedorDao dao;
    private final Scanner sc = new Scanner(System.in);

    public VendedorService() {
        this.dao = new VendedorDao();
    }

    public String salvar(Vendedor vendedor) {
        return this.dao.salvar(vendedor);
    }

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }

    public List<Vendedor> listar() {
        return this.dao.listar();
    }


    public void cadastrarPassageiro() {
        try {
            Passageiro passageiro = new Passageiro(); 
            System.out.println("Digite o nome do passageiro: ");
            passageiro.setNome(sc.nextLine());

            System.out.println("Digite o sexo do passageiro: ");
            passageiro.setSexo(sc.nextLine());

            System.out.println("Digite o email do passageiro: ");
            passageiro.setEmail(sc.nextLine());

            System.out.println("Digite a senha do passageiro: ");
            passageiro.setSenha(sc.nextLine());

            System.out.println("Digite o cpf do passageiro: ");
            passageiro.setCpf(sc.nextLine());

            
            PassageiroService passageiroService = new PassageiroService();
            passageiroService.salvar(passageiro);
            System.out.println("Passageiro cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar passageiro: " + e.getMessage());
        }
    }

    public void venderPassagem(Passagem passagem) {
        Passageiro passageiro = new Passageiro();
        try {
            System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
            passageiro.setNome(sc.nextLine());

            System.out.println("DIGITE O SEXO DO PASSAGEIRO: (M/F) ");
            passageiro.setSexo(sc.nextLine());

            System.out.println("DIGITE O EMAIL DO PASSAGEIRO: ");
            passageiro.setEmail(sc.nextLine());

            System.out.println("DIGITE SEU CPF: ");
            passageiro.setCpf(sc.nextLine());

            System.out.println("DIGITE SEU LOCAL DE ORIGEM: ");
            passagem.setOrigem(sc.nextLine());

            System.out.println("DIGITE SEU LOCAL DE DESTINO: ");
            passagem.setDestino(sc.nextLine());

            System.out.println("DIGITE A DATA DA VIAGEM (dd/mm/aaaa): ");
            passagem.setDataViagem(sc.nextLine());

            System.out.println("DIGITE O PREÇO DA PASSAGEM: ");
            passagem.setPreco(Double.parseDouble(sc.nextLine()));

            System.out.println("DIGITE O NÚMERO DO ASSENTO: ");
            passagem.setNumeroAssento(Integer.parseInt(sc.nextLine()));

            PassagemService passagemService = new PassagemService();
            passagemService.salvar(passagem);
            System.out.println("Passagem comprada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato numérico: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao comprar passagem: " + e.getMessage());
        }
    }

    public void listarPassagem() {
        System.out.println(this.listar());
    }

    public void fecharScanner() {
        sc.close();
    }
}
