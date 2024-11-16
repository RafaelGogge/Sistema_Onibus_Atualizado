package br.com.unisales.table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.unisales.entities.TipoUsuario;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoUsuario", nullable = false, unique = true)
    private TipoUsuario tipoUsuario;

    public void setCelular(String nextLine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCelular'");
    }

}
