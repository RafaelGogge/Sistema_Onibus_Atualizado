package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.table.Administrador;
import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Onibus;
import br.com.unisales.table.Assento;
import br.com.unisales.entities.TipoUsuario;
import br.com.unisales.dao.AdministradorDao;

public class AdministradorService {
    private final AdministradorDao dao;
    private final Scanner sc = new Scanner(System.in);

    public AdministradorService() {
        this.dao = new AdministradorDao();
    }

    public String salvar(Administrador administrador) {
        return this.dao.salvar(administrador);
    }

    public String alterar(Administrador administrador) {
        return this.dao.alterar(administrador);
    }

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }

    public List<Administrador> listar() {
        return this.dao.listar();
    }

    public void cadastrarPassageiro(Passageiro passageiro) {
        System.out.println("Digite o nome do passageiro: ");
        passageiro.setNome(sc.nextLine());
        System.out.println("Digite o sexo do passageiro: ");
        passageiro.setSexo(sc.nextLine());
        System.out.println("Digite o email do passageiro: ");
        passageiro.setEmail(sc.nextLine());
        System.out.println("Digite a senha do passageiro: ");
        passageiro.setSenha(sc.nextLine());
        passageiro.setTipoUsuario(TipoUsuario.PASSAGEIRO);

        PassageiroService passageiroService = new PassageiroService();
        passageiroService.salvar(passageiro);
        System.out.println("Passageiro cadastrado com sucesso!");
    }

    // Remover comentários de código não utilizado

    public void cadastrarOnibus(Onibus onibus) {
        System.out.println("Digite a placa do onibus: ");
        onibus.setPlaca(sc.nextLine());
        System.out.println("Digite o modelo do onibus: ");
        onibus.setQuantidadeAssento(sc.nextInt());
        sc.nextLine();

        OnibusService onibusService = new OnibusService();
        onibusService.salvar(onibus);

        System.out.println("Onibus cadastrado com sucesso!");
    }

    public void cadastrarAssento(Assento assento) {
        System.out.println("Digite o numero do assento: ");
        assento.setNumero(sc.nextInt());
        sc.nextLine();

        AssentoService assentoService = new AssentoService();
        assentoService.salvar(assento);

        System.out.println("Assento cadastrado com sucesso!");
    }
}
