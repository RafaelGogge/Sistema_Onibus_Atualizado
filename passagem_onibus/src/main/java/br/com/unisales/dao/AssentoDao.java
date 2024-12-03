package br.com.unisales.dao;

import br.com.unisales.table.Assento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class AssentoDao {
    private static final Logger LOGGER = Logger.getLogger(AssentoDao.class.getName());
    private EntityManagerFactory emf;

    public AssentoDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }

    public String salvar(Assento assento) {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(assento);
            em.getTransaction().commit();
            em.close();
            return "Sucesso ao salvar assento!";
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return "Erro ao salvar assento!";
        }
    }

    public List<Assento> listar(){
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Assento a", Assento.class).getResultList();
        } finally {
            em.close();
        }
    }
}
