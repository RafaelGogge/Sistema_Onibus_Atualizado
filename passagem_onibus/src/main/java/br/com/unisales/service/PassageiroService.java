package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.dao.PassageiroDao;
import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Passagem;


public class PassageiroService {


    private final PassageiroDao dao;
    Scanner sc = new Scanner(System.in);


    public PassageiroService() {
        this.dao = new PassageiroDao();
    }


    public String salvar(Passageiro passageiro) {
        return this.dao.salvar(passageiro);
    }


    public String alterar(Passageiro passageiro) {
        return this.dao.alterar(passageiro);
    }


    public String excluir(Long id) {
        return this.dao.excluir(id);
    }


    public List<Passageiro> listar() {
        return this.dao.listar();
    }


    public void comprarPassagem(Passagem passagem) {
        Passageiro passageiro = new Passageiro();
        System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
        passageiro.setNome(sc.nextLine());
        System.out.println("DIGITE O SEXO DO PASSAGEIRO: (M/F) ");
        passageiro.setSexo(sc.nextLine());
        System.out.println("DIGITE O EMAIL DO PASSAGEIRO: ");
        passageiro.setEmail(sc.nextLine());
        System.out.println("DIGITE SEU LOCAL DE PARTIDA: ");
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
        


        System.out.println(this.salvar(passageiro)); 
        System.out.println("Passagem comprada com sucesso!");

    }

    public void listarPassagem(Passageiro passageiro){
        System.out.println(this.listar());
    }
}
    

