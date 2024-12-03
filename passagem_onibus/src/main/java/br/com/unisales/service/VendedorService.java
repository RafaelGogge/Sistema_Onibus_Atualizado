package br.com.unisales.service;

import java.util.List;
import java.util.Scanner;

import br.com.unisales.dao.VendedorDao;
import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Passagem;
import br.com.unisales.table.Vendedor;
import br.com.unisales.entities.TipoUsuario;

// Criando a classe vendedor
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

    public void cadastrarPassageiro() {
        Passageiro passageiro = new Passageiro();
        try {
            System.out.println("DIGITE O CELULAR DO PASSAGEIRO: ");
            passageiro.setCelular(sc.nextLine());
    
            System.out.println("DIGITE O CPF DO PASSAGEIRO: ");
            String cpf = sc.nextLine();
            if (cpf.matches("\\d{11}")) { // Validação simples para 11 dígitos numéricos
                passageiro.setCpf(cpf);
            } else {
                System.out.println("CPF inválido. Por favor, digite um CPF com 11 dígitos.");
                return;
            }
    
            System.out.println("DIGITE O EMAIL DO PASSAGEIRO: ");
            String email = sc.nextLine();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                passageiro.setEmail(email);
            } else {
                System.out.println("E-mail inválido. Por favor, digite um e-mail válido.");
                return;
            }
    
            System.out.println("DIGITE O LOCAL DE DESTINO DO PASSAGEIRO: ");
            passageiro.setLocalDestino(sc.nextLine());
    
            System.out.println("DIGITE O LOCAL DE PARTIDA DO PASSAGEIRO: ");
            passageiro.setLocalPartida(sc.nextLine());
    
            System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
            String nome = sc.nextLine();
            if (nome.matches("[a-zA-Z\\s]+")) {
                passageiro.setNome(nome);
            } else {
                System.out.println("Nome inválido. Por favor, digite apenas letras e espaços.");
                return;
            }
    
            System.out.println("DIGITE A SENHA DO PASSAGEIRO: ");
            String senha = sc.nextLine();
            if (!senha.isEmpty()) {
                passageiro.setSenha(senha);
            } else {
                System.out.println("Senha não pode ser vazia.");
                return;
            }
    
            System.out.println("DIGITE O SEXO DO PASSAGEIRO: (M/F) ");
            String inputSexo = sc.nextLine().toUpperCase();
            if ("M".equals(inputSexo) || "F".equals(inputSexo)) {
                passageiro.setSexo(inputSexo);
            } else {
                System.out.println("Sexo inválido. Por favor, digite 'M' para masculino ou 'F' para feminino.");
                return;
            }
    
            System.out.println("DIGITE O TIPO DE USUÁRIO (ADMINISTRADOR/PASSAGEIRO): ");
            String tipoUsuarioInput = sc.nextLine().toUpperCase();
            try {
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoUsuarioInput);
                passageiro.setTipoUsuario(tipoUsuario);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de usuário inválido. Por favor, digite 'ADMINISTRADOR' ou 'PASSAGEIRO'.");
                return;
            }
    
            // Salvar passageiro
            System.out.println(this.dao.cadastrarPassageiro(passageiro));
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar passageiro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public String venderPassagem(Passagem passagem) {
        System.out.println("DIGITE O NOME DO PASSAGEIRO: ");
        String nome = sc.nextLine();
        Passageiro passageiro = new Passageiro();
        if (nome.matches("[a-zA-Z\\s]+")) { // Permitir espaços no nome
            passageiro.setNome(nome);
        } else {
            System.out.println("Nome inválido. Por favor, digite apenas letras.");
            return "Erro ao vender passagem!";
        }

        System.out.println("DIGITE O LOCAL DE PARTIDA DO PASSAGEIRO: ");
        passageiro.setLocalPartida(sc.nextLine());

        System.out.println("DIGITE O LOCAL DE DESTINO DO PASSAGEIRO: ");
        passageiro.setLocalDestino(sc.nextLine());

        System.out.println("DIGITE A DATA E O HORÁRIO DE PARTIDA DO PASSAGEIRO: ");
        passagem.setDataViagem(sc.nextLine()); // Alterado para definir a data da viagem na passagem

        PassagemService passagemService = new PassagemService();
        passagemService.salvar(passagem);
        return "Passagem vendida com sucesso!";
    }

    public void listarPassagem() {
        System.out.println(this.dao.listar());
    }
}
