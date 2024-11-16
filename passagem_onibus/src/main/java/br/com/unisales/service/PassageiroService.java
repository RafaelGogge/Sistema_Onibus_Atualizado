package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.dao.PassageiroDao;
import br.com.unisales.table.Passageiro;


public class PassageiroService {


    private final PassageiroDao dao;
    Scanner sc = new Scanner(System.in);


    public PassageiroService() {
        this.dao = new PassageiroDao();
    }


    public String salvar(Passageiro passageiro) {
        return this.dao.salvar(passageiro);
    }
    // Método para salvar um objeto Onibus utilizando o método salvar do OnibusDao.

    public String alterar(Passageiro passageiro) {
        return this.dao.alterar(passageiro);
    }
    // Método para alterar um objeto Onibus utilizando o método alterar do
    // AdministradorDao.

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }
    // Método para excluir um objeto Onibus pelo seu ID utilizando o método excluir
    // do AdministradorDao.

    public List<Passageiro> listar() {
        return this.dao.listar();
    }
    // Método para listar todos os objetos Onibus utilizando o método listar do
    // OnibusDao.

    public void comprarPassagem(Passageiro passageiro){
        System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
        passageiro.setNome(sc.nextLine());
        System.out.println("DIGITE O SEXO DO PASSAGEIRO: (M/F) ");
        passageiro.setSexo(sc.nextLine());
        System.out.println("DIGITE O EMAIL DO PASSAGEIRO: ");
        passageiro.setEmail(sc.nextLine());
        System.out.println("DIGITE SEU LOCAL DE PARTIDA: ");
        System.out.println("DIGITE SEU CPF: ");
        passageiro.setCpf(sc.nextLine());
        System.out.println("DIGITE SEU NÚMERO DE CELULAR COM DDD:");
        passageiro.setCelular(sc.nextLine());
        /*passageiro.setLocalPartida(sc.nextLine());
        System.out.println("DIGITE SEU LOCAL DE DESTINO: ");
        passageiro.setLocalDestino(sc.nextLine());
        System.out.println("DIGITE A DATA DE PARTIDA: ");
        passageiro.setDataPartida(sc.nextLine());
        */
        // COMENTEI PORQUE PRECISAREI DA TABELA PASSAGEM PARA SALVAR A DATA, DESTINO E ORIGEM

        System.out.println(this.salvar(passageiro)); 

    }

    public void listarPassagem(Passageiro passageiro){
        System.out.println(this.listar());
    }
}
    

