package br.com.unisales.table;
// Define o pacote onde a classe Onibus está localizada.

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Importa as anotações e funcionalidades necessárias do JPA e Lombok.

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "onibus")
// As anotações @Data, @NoArgsConstructor e @AllArgsConstructor são do Lombok, que gera getters, setters e construtores automaticamente.
// @Entity indica que essa classe é uma entidade JPA e @Table especifica a tabela do banco de dados correspondente.

public class Onibus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Id indica que 'id' é a chave primária da entidade.
    // @GeneratedValue com strategy = GenerationType.IDENTITY define que o valor do 'id' será gerado pelo banco de dados automaticamente.

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;
    // @Column indica que este campo será uma coluna na tabela 'onibus' com nome 'placa', não pode ser nulo e deve ser único.

    @Column(name = "quantidade_assento", nullable = false)
    private int quantidadeAssento;
    // @Column indica que este campo será uma coluna na tabela 'onibus' com nome 'quantidade_assento' e não pode ser nulo.
}
