package br.com.unisales.dao;

import br.com.unisales.table.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AdministradorDao {
    private EntityManagerFactory emf;

    public AdministradorDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }
    
    public String salvar(Administrador administrador) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(administrador);
            em.getTransaction().commit();
            return "Administrador salvo com sucesso!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar administrador!";
        } finally {
            em.close();
        }
    }

    public String alterar(Administrador administrador) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(administrador);
            em.getTransaction().commit();
            return "ADM alterado com sucesso!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar ADM!";
        } finally {
            em.close();
        }
    }

    public String excluir(Long id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Administrador administrador = em.find(Administrador.class, id);
            if (administrador != null) {
                em.remove(administrador);
                em.getTransaction().commit();
                return "Administrador excluído com sucesso!";
            } else {
                em.getTransaction().rollback();
                return "ADM não encontrado!";
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao excluir administrador!";
        } finally {
            em.close();
        }
    }

    public List<Administrador> listar() {
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Administrador p", Administrador.class).getResultList();
        } finally {
            em.close();
        }
    }
}
