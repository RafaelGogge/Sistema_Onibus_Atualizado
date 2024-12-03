package br.com.unisales.dao;

import java.util.List;

import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class VendedorDao {

    private EntityManagerFactory emf;

    public VendedorDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }

    public String salvar(Vendedor vendedor) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(vendedor);
            em.getTransaction().commit();
            return "Vendedor salvo com sucesso!";
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro de persistência: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar Vendedor!";
        } finally {
            em.close();
        }
    }

    public String alterar(Passageiro passageiro) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(passageiro);
            em.getTransaction().commit();
            return "Passageiro alterado com sucesso!";
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro de persistência: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar Passageiro!";
        } finally {
            em.close();
        }
    }

    public String cadastrarPassageiro(Passageiro passageiro) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(passageiro);
            em.getTransaction().commit();
            return "Passageiro salvo com sucesso!";
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro de persistência: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar Passageiro!";
        } finally {
            em.close();
        }
    }

    public List<Vendedor> listar() {
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vendedor v", Vendedor.class).getResultList();
        } finally {
            em.close();
        }
    }

    public String excluir(Long id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Vendedor vendedor = em.find(Vendedor.class, id);
            if (vendedor != null) {
                em.remove(vendedor);
                em.getTransaction().commit();
                return "Vendedor excluído com sucesso!";
            } else {
                return "Vendedor não encontrado!";
            }
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro de persistência: " + e.getMessage());
            e.printStackTrace();
            return "Erro ao excluir Vendedor!";
        } finally {
            em.close();
        }
    }
}
