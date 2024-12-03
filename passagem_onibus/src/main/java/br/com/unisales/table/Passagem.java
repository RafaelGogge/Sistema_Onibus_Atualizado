package br.com.unisales.table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passagem")
public class Passagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "origem", nullable = false, length = 100)
    private String origem;

    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    @Column(name = "data_viagem", nullable = false)
    private String dataViagem;

    @Column(name = "preco", nullable = false)
    private double preco;

    @Column(name = "numero_assento", nullable = false)
    private int numeroAssento;

    public void exibirDetalhes() {
        System.out.println("Passagem de: " + origem);
        System.out.println("Destino: " + destino);
        System.out.println("Data da Viagem: " + dataViagem);
        System.out.println("Preço: R$" + preco);
        System.out.println("Número do Assento: " + numeroAssento);
    }
}
