package br.com.unisales.table;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passageiro")

public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Id indica que 'id' é uma chave primária na tabela do banco de dados..

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    // @Column indica que 'nome' é uma coluna na tabela do banco de dados.

    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 10, unique = true)
    private String senha;



}
