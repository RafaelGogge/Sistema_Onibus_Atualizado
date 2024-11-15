package br.com.unisales.dao;
// Define o pacote onde a classe AssentoDao está localizada.

import br.com.unisales.table.Assento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

// Importa as classes necessárias para realizar operações de persistência (CRUD).

public class AssentoDao {
    private EntityManagerFactory emf;
    // Declara um objeto EntityManagerFactory, que será usado para criar
    // EntityManager's.

    public AssentoDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }
    // Construtor da classe AssentoDao, que inicializa o EntityManagerFactory usando
    // a unidade de persistência 'onibusPUSQLite'.

    public String salvar(Assento assento) {
        try {
            // Criando o objeto para realizar as operações de CRUD no banco de dados
            EntityManager em = this.emf.createEntityManager();
            // Inicializando uma transação com o banco de dados
            em.getTransaction().begin();
            // Preparando e validando os dados do novo assento para serem salvos no banco de
            // dados
            em.persist(assento);
            // Salvando os dados do novo assento no banco de dados
            em.getTransaction().commit();
            // Fechando a transação de inserção do novo assento no banco de dados
            em.close();
            return "Sucesso ao salvar assento!";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar assento!";
        }
    }
    /*
     * Método 'salvar' que recebe um objeto Assento e realiza a persistência no
     * banco de dados.
     * Abre uma transação, persiste o assento, faz commit e fecha a transação.
     * Retorna "sucesso" se tudo der certo, e "erro" se algo der errado, capturando
     * e imprimindo a exceção.
     */

    public List<Assento> listar(){
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Assento a", Assento.class).getResultList();
        } finally {
            em.close();
        }
    }
}