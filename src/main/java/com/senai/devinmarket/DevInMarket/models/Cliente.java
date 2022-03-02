package com.senai.devinmarket.DevInMarket.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente implements Comparable<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = false)
    private String cpf;

    @NonNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @ManyToMany
    @JoinTable(
            name = "produto_cliente",
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_produto", "id_cliente"}),
            joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private Set<Produto> produtos = new TreeSet<>();

    @Override
    public int compareTo(Cliente o) {
        return this.id.compareTo(o.getId());
    }
}
