package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.exceptions.EstoqueAindaDisponivelException;
import com.talles.GerenciadorComandas.exceptions.ProdutoNotFoundException;
import com.talles.GerenciadorComandas.mapper.ProdutoMapper;
import com.talles.GerenciadorComandas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final EstoqueService estoqueService;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper,EstoqueService estoqueService) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
        this.estoqueService = estoqueService;

    }


    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO produtoDTO){
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
        estoqueService.criarEstoque(produto);
        return produtoMapper.toDTO(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtosList = produtoRepository.findByAtivoTrue();

        return produtosList.stream()
                .map(produtoMapper::toDTO).toList();
    }
    public ProdutoResponseDTO produtoById(Long id){
        Optional<Produto> produto = produtoRepository.findByIdAndAtivoTrue(id);
        if(produto.isEmpty()) {
            throw new ProdutoNotFoundException();
        }
        return produtoMapper.toDTO(produto.get());
    }
    public void deletarProdutoById(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findByIdAndAtivoTrue(id);
        if (produtoOptional.isEmpty()) {
            throw new ProdutoNotFoundException();
        }
        Produto produto = produtoOptional.get();
        if (estoqueService.buscarEstoquePorProdutoId(produto.getId()).getQuantidade() != 0) {
            throw new EstoqueAindaDisponivelException();
        }

        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoAtualizado){
       Optional<Produto> produtoOptional = produtoRepository.findByIdAndAtivoTrue(id);
       if (produtoOptional.isEmpty()) {
           throw new ProdutoNotFoundException();
       }
       Produto produto = produtoOptional.get();


        if (produtoAtualizado.getNome() != null) {
            produto.setNome(produtoAtualizado.getNome());
            estoqueService.atualizarNomeEstoque(id,produtoAtualizado.getNome());
        }
        if (produtoAtualizado.getPreco() != null) {
            produto.setPreco(produtoAtualizado.getPreco());
        }
        if (produtoAtualizado.getCodigoDeBarras() != null) {
            produto.setCodigoDeBarras(produtoAtualizado.getCodigoDeBarras());
        }
            produtoRepository.save(produto);

            return  produtoMapper.toDTO(produto);
    }


}
