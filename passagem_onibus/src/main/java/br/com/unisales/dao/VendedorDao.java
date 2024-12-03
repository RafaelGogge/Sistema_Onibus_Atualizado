package br.com.unisales.dao;

import br.com.unisales.table.Passageiro;
import br.com.unisales.table.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.logging.Logger;

public class VendedorDao {

    private static final Logger LOGGER = Logger.getLogger(VendedorDao.class.getName());
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
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar Vendedor!";
        } finally {
            em.close();
        }
    }

    // Método para o vendedor alterar o cadastro do passageiro
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
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar Passageiro!";
        } finally {
            em.close();
        }
    }

    // Método para o vendedor salvar o passageiro que não fez o próprio cadastro
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
            LOGGER.severe(e.getMessage());
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
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return "Erro ao excluir Vendedor!";
        } finally {
            em.close();
        }
    }
}
