package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.dao.PassagemDao;
import br.com.unisales.table.Passagem;

public class PassagemService {

    private final PassagemDao dao;
    private final Scanner sc = new Scanner(System.in);

    public PassagemService() {
        this.dao = new PassagemDao();
    }

    public String salvar(Passagem passagem) {
        return this.dao.salvar(passagem);
    }

    public String alterar(Passagem passagem) {
        return this.dao.alterar(passagem);
    }

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }

    public List<Passagem> listar() {
        return this.dao.listar();
    }

    public void comprarPassagem(Passagem passagem) {
        System.out.println("DIGITE A ORIGEM: ");
        passagem.setOrigem(sc.nextLine());
        System.out.println("DIGITE O DESTINO: ");
        passagem.setDestino(sc.nextLine());
        System.out.println("DIGITE A DATA DA VIAGEM (dd/mm/aaaa): ");
        passagem.setDataViagem(sc.nextLine());
        System.out.println("DIGITE O PREÇO DA PASSAGEM: ");
        passagem.setPreco(Double.parseDouble(sc.nextLine()));
        System.out.println("DIGITE O NÚMERO DO ASSENTO: ");
        passagem.setNumeroAssento(Integer.parseInt(sc.nextLine()));

        System.out.println(this.salvar(passagem));
    }

    public void listarPassagens() {
        List<Passagem> passagens = this.listar();
        if (passagens.isEmpty()) {
            System.out.println("Nenhuma passagem encontrada.");
        } else {
            passagens.forEach(Passagem::exibirDetalhes);
        }
    }
}
