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
            // Cria um EntityManager para gerenciar as operações de persistência.

            em.getTransaction().begin();
            // Inicia uma transação.

            em.merge(onibus);
            // Atualiza o objeto Onibus no banco de dados.

            em.getTransaction().commit();
            // Confirma a transação, efetivando as alterações.

            em.close();
            // Fecha o EntityManager.

            return "Ônibus alterado com sucesso!";
            // Retorna uma mensagem de sucesso se a operação for concluída sem erros.
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            // Imprime a mensagem de erro e a pilha de exceções no console.

            return "Erro ao alterar ônibus!";
            // Retorna uma mensagem de erro se ocorrer alguma exceção.
        }
    }

    public String excluir(Long id) {
        try {
            EntityManager em = this.emf.createEntityManager();
            // Cria um EntityManager para gerenciar as operações de persistência.

            em.getTransaction().begin();
            // Inicia uma transação.

            Onibus onibus = em.find(Onibus.class, id);
            // Busca um objeto Onibus pelo seu ID.

            if (onibus != null) {
                em.remove(onibus);
                // Remove o objeto Onibus do banco de dados se ele existir.

                em.getTransaction().commit();
                // Confirma a transação, efetivando a remoção.

                em.close();
                // Fecha o EntityManager.

                return "Ônibus excluído com sucesso!";
                // Retorna uma mensagem de sucesso se a operação for concluída sem erros.
            } else {
                em.getTransaction().rollback();
                // Reverte a transação se o objeto Onibus não for encontrado.

                em.close();
                // Fecha o EntityManager.

                return "Ônibus não encontrado!";
                // Retorna uma mensagem indicando que o objeto Onibus não foi encontrado.
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            // Imprime a mensagem de erro e a pilha de exceções no console.

            return "Erro ao excluir ônibus!";
            // Retorna uma mensagem de erro se ocorrer alguma exceção.
        }
    }

    public List<Onibus> listar() {
        EntityManager em = this.emf.createEntityManager();
        // Cria um EntityManager para gerenciar as operações de persistência.

        try {
            return em.createQuery("SELECT o FROM Onibus o", Onibus.class).getResultList();
            // Executa uma consulta JPQL para listar todos os objetos Onibus.

        } finally {
            em.close();
            // Fecha o EntityManager, garantindo que ele seja fechado mesmo em caso de
            // exceção.
        }
    }
}
