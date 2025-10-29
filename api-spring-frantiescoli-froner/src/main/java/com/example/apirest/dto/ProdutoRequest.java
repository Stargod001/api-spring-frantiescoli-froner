package com.example.apirest.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;
    @NotNull
    @PositiveOrZero
    private Integer estoque;
    private String descricao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
