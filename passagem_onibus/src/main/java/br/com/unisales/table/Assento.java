package br.com.unisales.table;
// Isso define o pacote onde a classe Assento está localizada.

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Essas linhas importam as anotações e funcionalidades necessárias do JPA (Jakarta Persistence API) e Lombok.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assento")
// As anotações @Data, @NoArgsConstructor e @AllArgsConstructor são do Lombok, que gera getters, setters e construtores automaticamente.
// @Entity indica que essa classe é uma entidade JPA e @Table especifica a tabela do banco de dados correspondente.

public class Assento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Id indica que 'id' é a chave primária da entidade.
    // @GeneratedValue com strategy = GenerationType.IDENTITY define que o valor do 'id' será gerado pelo banco de dados automaticamente.

    @Column(name = "numero", nullable = false)
    private int numero;
    // @Column indica que este campo será uma coluna na tabela 'assento' com nome 'numero' e não pode ser nulo.

    @ManyToOne(targetEntity=Onibus.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "id_onibus")
    private Onibus onibus;
    // @ManyToOne estabelece uma relação muitos-para-um com a entidade 'Onibus'.
    // targetEntity define a classe da entidade relacionada.
    // fetch = FetchType.EAGER significa que os dados do 'Onibus' serão carregados imediatamente junto com o 'Assento'.
    // @JoinColumn especifica a coluna que será usada para o relacionamento no banco de dados, neste caso, 'id_onibus'.
}