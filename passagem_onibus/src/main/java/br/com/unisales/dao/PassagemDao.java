package br.com.unisales.dao;

import br.com.unisales.table.Passagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class PassagemDao {
    private static final Logger LOGGER = Logger.getLogger(PassagemDao.class.getName());
    private static final String PERSISTENCE_UNIT_NAME = "onibusPUSQLite";
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public PassagemDao() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.em = emf.createEntityManager();
    }

    public String salvar(Passagem passagem) {
        try {
            em.getTransaction().begin();
            em.persist(passagem);
            em.getTransaction().commit();
            return "Passagem salva com sucesso!";
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.severe(e.getMessage());
            return "Erro ao salvar passagem: " + e.getMessage();
        }
    }

    public String alterar(Passagem passagem) {
        try {
            em.getTransaction().begin();
            em.merge(passagem);
            em.getTransaction().commit();
            return "Passagem alterada com sucesso!";
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.severe(e.getMessage());
            return "Erro ao alterar passagem: " + e.getMessage();
        }
    }

    public String excluir(Long id) {
        try {
            em.getTransaction().begin();
            Passagem passagem = em.find(Passagem.class, id);
            if (passagem != null) {
                em.remove(passagem);
                em.getTransaction().commit();
                return "Passagem excluída com sucesso!";
            } else {
                return "Passagem não encontrada.";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.severe(e.getMessage());
            return "Erro ao excluir passagem: " + e.getMessage();
        }
    }

    public List<Passagem> listar() {
        try {
            return em.createQuery("SELECT p FROM Passagem p", Passagem.class).getResultList();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return List.of();
        }
    }

    public void fechar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
