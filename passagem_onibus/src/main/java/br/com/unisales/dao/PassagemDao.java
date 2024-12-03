package br.com.unisales.dao;

import br.com.unisales.table.Passagem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PassagemDao {

    private static final String PERSISTENCE_UNIT_NAME = "your-persistence-unit-name"; // Substitua pelo nome da unidade de persistência
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public PassagemDao() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.em = emf.createEntityManager();
    }

    // Método para salvar uma passagem
    public String salvar(Passagem passagem) {
        try {
            em.getTransaction().begin();
            em.persist(passagem);
            em.getTransaction().commit();
            return "Passagem salva com sucesso!";
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Erro ao salvar passagem: " + e.getMessage();
        }
    }

    // Método para alterar uma passagem
    public String alterar(Passagem passagem) {
        try {
            em.getTransaction().begin();
            em.merge(passagem);
            em.getTransaction().commit();
            return "Passagem alterada com sucesso!";
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Erro ao alterar passagem: " + e.getMessage();
        }
    }

    // Método para excluir uma passagem pelo ID
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
            return "Erro ao excluir passagem: " + e.getMessage();
        }
    }

    // Método para listar todas as passagens
    public List<Passagem> listar() {
        try {
            return em.createQuery("SELECT p FROM Passagem p", Passagem.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar passagens: " + e.getMessage());
            return List.of();
        }
    }

    // Fechar o EntityManager e o EntityManagerFactory
    public void fechar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
