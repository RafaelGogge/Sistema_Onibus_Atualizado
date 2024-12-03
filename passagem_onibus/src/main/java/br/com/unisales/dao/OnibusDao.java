package br.com.unisales.dao;

import br.com.unisales.table.Onibus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class OnibusDao {
    private final EntityManagerFactory emf;

    public OnibusDao() {
        this.emf = Persistence.createEntityManagerFactory("onibusPUSQLite");
    }

    public String salvar(Onibus onibus) {
        try (EntityManager em = this.emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(onibus);
            em.getTransaction().commit();
            return "Onibus salvo com sucesso!";
        } catch (Exception e) {
            return tratarExcecao(e, "Erro ao salvar onibus!");
        }
    }

    public String alterar(Onibus onibus) {
        try (EntityManager em = this.emf.createEntityManager()) {
            em.getTransaction().begin();
            Onibus onibusExistente = em.find(Onibus.class, onibus.getId());
            if (onibusExistente == null) {
                em.getTransaction().rollback();
                return "Ônibus não encontrado!";
            }

            if (!onibusExistente.getPlaca().equals(onibus.getPlaca())) {
                List<Onibus> onibusComPlaca = em.createQuery("SELECT o FROM Onibus o WHERE o.placa = :placa", Onibus.class)
                        .setParameter("placa", onibus.getPlaca()).getResultList();
                if (!onibusComPlaca.isEmpty()) {
                    em.getTransaction().rollback();
                    return "Placa já existe!";
                }
            }

            em.merge(onibus);
            em.getTransaction().commit();
            return "Ônibus alterado com sucesso!";
        } catch (Exception e) {
            return tratarExcecao(e, "Erro ao alterar ônibus!");
        }
    }

    public String excluir(Long id) {
        try (EntityManager em = this.emf.createEntityManager()) {
            em.getTransaction().begin();
            Onibus onibus = em.find(Onibus.class, id);
            if (onibus != null) {
                List<Viagem> viagens = em.createQuery("SELECT v FROM Viagem v WHERE v.onibus.id = :id", Viagem.class)
                        .setParameter("id", id).getResultList();
                if (!viagens.isEmpty()) {
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
            return tratarExcecao(e, "Erro ao excluir ônibus!");
        }
    }

    public List<Onibus> listar() {
        try (EntityManager em = this.emf.createEntityManager()) {
            return em.createQuery("SELECT o FROM Onibus o", Onibus.class).getResultList();
        } catch (Exception e) {
            tratarExcecao(e, "Erro ao listar ônibus!");
            return null;
        }
    }

    private String tratarExcecao(Exception e, String mensagem) {
        System.err.println(mensagem + " " + e.getMessage());
        return mensagem;
    }
}
