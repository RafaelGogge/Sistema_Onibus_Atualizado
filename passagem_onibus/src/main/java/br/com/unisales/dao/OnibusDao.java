package br.com.unisales.dao;

import br.com.unisales.table.Onibus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class OnibusDao {
    private EntityManagerFactory emf;

    public OnibusDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }

    public String salvar(Onibus onibus) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(onibus);
            em.getTransaction().commit();
            return "Onibus salvo com sucesso!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao salvar onibus!";
        } finally {
            em.close();
        }
    }

    public String alterar(Onibus onibus) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Onibus onibusExistente = em.find(Onibus.class, onibus.getId());
            if (onibusExistente == null) {
                return "Ônibus não encontrado!";
            }

            // Verificar se a placa é diferente e se já existe no banco de dados
            if (!onibusExistente.getPlaca().equals(onibus.getPlaca())) {
                List<Onibus> onibusComPlaca = em.createQuery("SELECT o FROM Onibus o WHERE o.placa = :placa", Onibus.class)
                        .setParameter("placa", onibus.getPlaca()).getResultList();
                if (!onibusComPlaca.isEmpty()) {
                    return "Placa já existe!";
                }
            }

            em.merge(onibus);
            em.getTransaction().commit();
            return "Ônibus alterado com sucesso!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao alterar ônibus!";
        } finally {
            em.close();
        }
    }

    public String excluir(Long id) {
        EntityManager em = this.emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Onibus onibus = em.find(Onibus.class, id);
            if (onibus != null) {
                // Verificar viagens associadas aqui. Suponhamos que exista uma entidade Viagem relacionada ao Ônibus.
                List<Viagem> viagens = em.createQuery("SELECT v FROM Viagem v WHERE v.onibus.id = :id", Viagem.class)
                        .setParameter("id", id).getResultList();
                if (!viagens.isEmpty()) {
                    // Mostrar janela para usuário decidir. Aqui, fazemos uma simulação da escolha.
                    boolean escolhaExcluir = true; // Esta variável deve ser determinada pela escolha do usuário
                    if (escolhaExcluir) {
                        for (Viagem viagem : viagens) {
                            em.remove(viagem);
                        }
                        em.remove(onibus);
                        em.getTransaction().commit();
                        return "Ônibus e viagens associadas excluídos com sucesso!";
                    } else {
                        em.getTransaction().rollback();
                        return "Exclusão cancelada pelo usuário.";
                    }
                } else {
                    em.remove(onibus);
                    em.getTransaction().commit();
                    return "Ônibus excluído com sucesso!";
                }
            } else {
                em.getTransaction().rollback();
                return "Ônibus não encontrado!";
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println(e.getMessage());
            e.printStackTrace();
            return "Erro ao excluir ônibus!";
        } finally {
            em.close();
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
