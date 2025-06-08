package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.EstoqueRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.EstoqueResponseDTO;
import com.talles.GerenciadorComandas.entity.Estoque;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.exceptions.*;
import com.talles.GerenciadorComandas.mapper.EstoqueMapper;
import com.talles.GerenciadorComandas.repository.EstoqueRepository;
import com.talles.GerenciadorComandas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, EstoqueMapper estoqueMapper, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.estoqueMapper = estoqueMapper;
        this.produtoRepository = produtoRepository;
    }

    public void criarEstoque(Produto produto){
        Estoque estoque = new Estoque(produto);
        estoqueRepository.save(estoque);
    }


    public EstoqueResponseDTO adicionarQuantidadeEstoque(Long id, EstoqueRequestDTO estoqueDTO){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNotFoundException::new);
        if (!produto.isAtivo()){
            throw new ProdutoInativoExcepiton();
        }
        adicionarSaldo(id, estoqueDTO.getQuantidade());
        return estoqueMapper.toDTO(estoqueRepository.findById(id)
                .orElseThrow(EstoqueProdutoNotFoundException::new));
    }
    public EstoqueResponseDTO buscarEstoquePorProdutoId(Long id){
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        return estoqueMapper.toDTO(estoque);

    }
    public List<EstoqueResponseDTO> listarEstoque(){
        List<Estoque> listEstoque = estoqueRepository.findAll();
        return listEstoque.stream()
                .map(estoqueMapper::toDTO).toList();
    }

    public void adicionarSaldo(Long idProduto, int quantidade){
        Estoque estoque = estoqueRepository.findById(idProduto)
                .orElseThrow(EstoqueProdutoNotFoundException::new);
        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        estoqueRepository.save(estoque);
    }

    public void subtrairSaldo(Long idProduto, int quantidade){
        Estoque estoque = estoqueRepository.findById(idProduto)
                .orElseThrow(EstoqueProdutoNotFoundException::new);
        if (estoque.getQuantidade() < quantidade) {
            throw new QuantidadeInsuficienteException("Quantidade a ser removida é maior que o saldo disponivel");
        }
        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        estoqueRepository.save(estoque);
    }

    public void deletarEstoque(Long id) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(EstoqueProdutoNotFoundException::new);
        if (estoque.getQuantidade() != 0){
            throw new EstoqueAindaDisponivelException();
        }
        estoqueRepository.delete(estoque);
    }

    public EstoqueResponseDTO subtrairQuantidadeEstoque(Long id, int quantidade){
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(EstoqueProdutoNotFoundException::new);
        if (estoque.getQuantidade() == quantidade) {
            estoque.setQuantidade(0);
        }else {
            subtrairSaldo(id, quantidade);
        }
        return estoqueMapper.toDTO(estoque);
    }

    public void atualizarNomeEstoque(Long idProduto,String nome) {
        Estoque estoque = estoqueRepository.findById(idProduto)
                .orElseThrow(EstoqueProdutoNotFoundException::new);
        estoque.setNomeProduto(nome);


    }
}
