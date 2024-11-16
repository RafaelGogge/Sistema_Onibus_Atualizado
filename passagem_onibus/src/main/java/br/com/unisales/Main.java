package br.com.unisales;

import br.com.unisales.service.AssentoService;
import br.com.unisales.service.OnibusService;
import br.com.unisales.table.Assento;
import br.com.unisales.table.Onibus;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        OnibusService oservice = new OnibusService();
        AssentoService aservice = new AssentoService();
        

        // Cadastrando e salvando um novo ônibus
        System.out.println("\n=== CADASTRO DE ÔNIBUS ===");

        Onibus onibus1 = new Onibus(null, "ABC1234", 50);
        System.out.println("Inserir novo ônibus: " + oservice.salvar(onibus1));

        Onibus onibus2 = new Onibus(null, "DEF5678", 40);
        System.out.println("Inserir novo ônibus: " + oservice.salvar(onibus2));



        // Alterando dados do ônibus -- VERIFICAR DEPOIS

        System.out.println("\n=== ALTERAÇÃO DE ÔNIBUS ===");

        onibus1.setPlaca("XYZ5678");
        onibus1.setQuantidadeAssento(45);

        System.out.println("Alterar dados do ônibus: " + oservice.alterar(onibus1) + oservice.salvar(onibus1));

        // VERIFICAR DEPOIS ALTERAÇÃO DE ONIBUS


        // Excluindo dados do onibus
        //System.out.println("\n=== EXCLUSÃO DE ÔNIBUS ===");

        //System.out.println("Excluir ônibus: " + oservice.excluir(onibus2.getId()));

        // Listando todos os ônibus

        System.out.println("\n=== LISTA DE ÔNIBUS ===");

        List<Onibus> onibusList = oservice.listar();
        for (Onibus o : onibusList) {
            System.out.println("Ônibus ID: " + o.getId() + ", Placa: " + o.getPlaca() + ", Quantidade de Assentos: "
                    + o.getQuantidadeAssento());
        }

        /*
         * 
         * TRABALHANDO COM O ASSENTO
         * 
         */

        // Criando e salvando novos assentos
        System.out.println("\n=== CADASTRO DE ASSENTOS ===");

        Assento assento1 = new Assento(null, 20, onibus1);
        Assento assento2 = new Assento(null, 25, onibus1);
        System.out.println("Inserir e salvar novo assento: " + aservice.salvar(assento1));
        System.out.println("Inserir e salvar novo assento: " + aservice.salvar(assento2));

        // Listando todos os assentos

        System.out.println("\n=== LISTA DE ASSENTOS ===");

        List<Assento> assentos = aservice.listar();
        for (Assento a : assentos) {
            System.out.println("Assento ID: " + a.getId() + ", Número do assento: " + a.getNumero());
        }
    }
}