package br.com.unisales.service;
// Define o pacote onde a classe AssentoService está localizada.

import java.util.List;

import br.com.unisales.dao.AssentoDao;
import br.com.unisales.table.Assento;
// Importa as classes AssentoDao e Assento, que são necessárias para os métodos dessa classe.

public class AssentoService {
    private AssentoDao dao;
    // Declara um objeto privado AssentoDao que será usado para interagir com a
    // camada de persistência.

    public AssentoService() {
        this.dao = new AssentoDao();
    }
    // Construtor da classe AssentoService que inicializa o objeto AssentoDao.

    public String salvar(Assento assento) {
        return this.dao.salvar(assento);
    }
    // Método público 'salvar' que recebe um objeto Assento como parâmetro e chama o
    // método 'salvar' do AssentoDao.
    // Retorna uma String indicando o resultado da operação ('sucesso' ou 'erro').

    public List<Assento> listar() {
        return this.dao.listar();
    }
}