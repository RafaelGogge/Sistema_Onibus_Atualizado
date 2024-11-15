package br.com.unisales.service;
// Define o pacote onde a classe OnibusService está localizada.

import br.com.unisales.dao.OnibusDao;
import br.com.unisales.table.Onibus;
import java.util.List;
// Importa as classes OnibusDao e Onibus, que são necessárias para os métodos dessa classe.

public class OnibusService {
    private OnibusDao dao;
    // Declara um objeto privado OnibusDao que será usado para interagir com a
    // camada de persistência.

    public OnibusService() {
        this.dao = new OnibusDao();
    }
    // Construtor da classe OnibusService que inicializa o objeto OnibusDao.

    public String salvar(Onibus onibus) {
        return this.dao.salvar(onibus);
    }
    // Método para salvar um objeto Onibus utilizando o método salvar do OnibusDao.

    public String alterar(Onibus onibus) {
        return this.dao.alterar(onibus);
    }
    // Método para alterar um objeto Onibus utilizando o método alterar do
    // OnibusDao.

    public String excluir(Long id) {
        return this.dao.excluir(id);
    }
    // Método para excluir um objeto Onibus pelo seu ID utilizando o método excluir
    // do OnibusDao.

    public List<Onibus> listar() {
        return this.dao.listar();
    }
    // Método para listar todos os objetos Onibus utilizando o método listar do
    // OnibusDao.
}
