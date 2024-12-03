package br.com.unisales.dao;

import br.com.unisales.table.Passageiro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class PassageiroDao {
    private static final Logger LOGGER = Logger.getLogger(PassageiroDao.class.getName());
    private EntityManagerFactory emf;

    public PassageiroDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }

    public String salvar(Passageiro passageiro) {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(passageiro);
            em.getTransaction().commit();
            em.close();
            return "Passageiro salvo com sucesso!";
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar Passageiro!";
        }
    }

    public String alterar(Passageiro passageiro) {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(passageiro);
            em.getTransaction().commit();
            em.close();
            return "Passageiro alterado com sucesso!";
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar passageiro!";
        }
    }

    public String excluir(Long id) {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            Passageiro passageiro = em.find(Passageiro.class, id);
            if (passageiro != null) {
                em.remove(passageiro);
                em.getTransaction().commit();
                em.close();
                return "Passageiro excluído com sucesso!";
            } else {
                em.getTransaction().rollback();
                em.close();
                return "Passageiro não encontrado!";
            }
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return "Erro ao excluir Passageiro!";
        }
    }

    public List<Passageiro> listar() {
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Passageiro p", Passageiro.class).getResultList();
        } finally {
            em.close();
        }
    }
}
