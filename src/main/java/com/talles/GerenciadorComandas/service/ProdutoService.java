package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.exceptions.ProdutoNotFoundException;
import com.talles.GerenciadorComandas.mapper.ProdutoMapper;
import com.talles.GerenciadorComandas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Produto> produtosList = produtoRepository.findAll();

        return produtosList.stream()
                .map(produtoMapper::toDTO).toList();

    }
    public ProdutoResponseDTO produtoById(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNotFoundException::new);
        return produtoMapper.toDTO(produto);
    }
    public void deletarProdutoById(Long id){
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException();
        }
        produtoRepository.deleteById(id);

    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoAtualizado){
       Produto produto = produtoRepository.findById(id)
               .orElseThrow(ProdutoNotFoundException::new);


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
