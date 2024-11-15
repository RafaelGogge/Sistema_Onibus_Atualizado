package br.com.unisales.dao;
// Define o pacote onde a classe OnibusDao está localizada.

import br.com.unisales.table.Onibus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

// Importa as classes necessárias para realizar operações de persistência (CRUD).

public class OnibusDao {
    private EntityManagerFactory emf;
    // Declara um objeto EntityManagerFactory, que será usado para criar
    // EntityManager's.

    public OnibusDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }
    // Construtor da classe OnibusDao, que inicializa o EntityManagerFactory usando
    // a unidade de persistência 'onibusPUSQLite'.

    public String salvar(Onibus onibus) {
        try {
            // Criando o objeto para realizar as operações de CRUD no banco de dados
            EntityManager em = this.emf.createEntityManager();
            // Inicializando uma transação com o banco de dados
            em.getTransaction().begin();
            // Preparando e validando os dados do novo ônibus para serem salvos no banco de
            // dados
            em.persist(onibus);
            // Salvando os dados do novo ônibus no banco de dados
            em.getTransaction().commit();
            // Fechando a transação de inserção do novo ônibus no banco de dados
            em.close();
            return "Onibus salvo com sucesso!";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar onibus!";
        }
    }

    /*
     * Método 'salvar' que recebe um objeto Onibus e realiza a persistência no banco
     * de dados.
     * Abre uma transação, persiste o ônibus, faz commit e fecha a transação.
     * Retorna "sucesso" se tudo der certo, e "erro" se algo der errado,
     * capturando
     * e imprimindo a exceção.
     */

    public String alterar(Onibus onibus) {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(onibus);
            em.getTransaction().commit();
            em.close();
            return "Ônibus alterado com sucesso!";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar ônibus!";
        }
    }

    public String excluir(Long id) {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            Onibus onibus = em.find(Onibus.class, id);
            if (onibus != null) {
                em.remove(onibus);
                em.getTransaction().commit();
                em.close();
                return "Ônibus excluído com sucesso!";
            } else {
                em.getTransaction().rollback();
                em.close();
                return "Ônibus não encontrado!";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao excluir ônibus!";
        }
    }

    public List<Onibus> listar() {
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT o FROM Onibus o", Onibus.class).getResultList();
        } finally {
            em.close();
        }
    }
}
