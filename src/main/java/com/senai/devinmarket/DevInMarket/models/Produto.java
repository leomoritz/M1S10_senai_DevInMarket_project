package com.senai.devinmarket.DevInMarket.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto implements Comparable<Cliente>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    private String descricao;

    @NonNull
    @Column(nullable = false)
    private Double preco;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "produto_cliente",
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_produto", "id_cliente"}),
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_cliente")
    )
    private Set<Cliente> cliente = new TreeSet<>();

    @Override
    public int compareTo(Cliente o) {
        return this.id.compareTo(o.getId());
    }
}
