package br.com.unisales.dao;

import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar Vendedor!";
        } finally {
            em.close();
        }
    }

    // método para o vendedor alterar o cadastro do passageiro
    public String alterar(Passageiro passageiro) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(passageiro);
            em.getTransaction().commit();
            return "Passageiro alterado com sucesso!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar Passageiro!";
        } finally {
            em.close();
        }
    }

    // metodo para o vendedor salvar o passageiro que não fez o proprio cadastro
    public String cadastrarPassageiro(Passageiro passageiro) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(passageiro);
            em.getTransaction().commit();
            return "Passageiro salvo com sucesso!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar Passageiro!";
        } finally {
            em.close();
        }
    }

    public List<Vendedor> listar() {
        EntityManager em = this.emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Vendedor p", Vendedor.class).getResultList();
        } finally {
            em.close();
        }
    }

}
