package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.dao.VendedorDao;
import br.com.unisales.table.Vendedor;
import br.com.unisales.table.Passageiro;

//Criando a classe vendedor
public class VendedorService {

    private final VendedorDao dao;
    private final Scanner sc = new Scanner(System.in);

    public VendedorService() {
        this.dao = new VendedorDao();
    }

    public String salvar(Vendedor vendedor) {
        return this.dao.salvar(vendedor);
    }

    public String alterar(Passageiro passageiro) {
        return this.dao.alterar(passageiro);
    }

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }

    public List<Vendedor> listar() {
        return this.dao.listar();
    }

    public void cadastrarPassageiro(Vendedor vendedor){
        System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
        String nome = sc.nextLine();
        Passageiro passageiro = new Passageiro();
        if (nome.matches("[a-zA-Z]+")) {
            passageiro.setNome(nome);
        } else {
            System.out.println("Nome inválido. Por favor, digite apenas letras.");
            return;
        }

        //cadastrar o sexo do passageiro, só podendo ser M ou F.
        System.out.println("DIGITE O SEXO DO PASSAGEIRO:  (M/F) ");
        String inputSexo = sc.nextLine().toUpperCase();
        if ("M".equals(inputSexo) || "F".equals(inputSexo)) {
            passageiro.setSexo(inputSexo);
        } else {
            System.out.println("Sexo inválido. Por favor, digite 'M' para masculino ou 'F' para feminino.");
            return;
        }

        System.out.println("DIGITE O EMAIL DO PASSAGEIRO: ");
        passageiro.setEmail(sc.nextLine());

        System.out.println("DIGITE O LOCAL DE DESTINO DO PASSAGEIRO: ");
        passageiro.setLocalDestino(sc.nextLine());

        System.out.println(this.dao.cadastrarPassageiro(passageiro));
    }

    public String venderPassagem(Vendedor vendedor) {
        System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
        String nome = sc.nextLine();
        Passageiro passageiro = new Passageiro();
        if (nome.matches("[a-zA-Z]+")) {
            passageiro.setNome(nome);
        } else {
            System.out.println("Nome inválido. Por favor, digite apenas letras.");
            return "Erro ao vender passagem!";
        }

        System.out.println("DIGITE O LOCAL DE PARTIDA DO PASSAGEIRO: ");
        passageiro.setLocalPartida(sc.nextLine());

        System.out.println("DIGITE O LOCAL DE DESTINO DO PASSAGEIRO: ");
        passageiro.setLocalDestino(sc.nextLine());

        System.out.println("DIGITE A DATA E O HORARIO DE PARTIDA DO PASSAGEIRO: ");
        passageiro.setLocalPartida(sc.nextLine());

        System.out.println(this.salvar(vendedor));
        return "Passagem vendida com sucesso!";
    }

    public void listarPassagem() {
        System.out.println(this.listar());
    }
}
