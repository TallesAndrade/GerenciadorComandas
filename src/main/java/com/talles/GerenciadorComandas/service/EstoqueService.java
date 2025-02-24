package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.EstoqueDTO;
import com.talles.GerenciadorComandas.entity.Estoque;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.mapper.EstoqueMapper;
import com.talles.GerenciadorComandas.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;

    public EstoqueService(EstoqueRepository estoqueRepository, EstoqueMapper estoqueMapper) {
        this.estoqueRepository = estoqueRepository;
        this.estoqueMapper = estoqueMapper;
    }

    public void criarEstoque(Produto produto){
        Estoque estoque = new Estoque(produto);
        estoqueRepository.save(estoque);
    }


    public EstoqueDTO adicionarQuantidadeEstoque(Long id,EstoqueDTO estoqueDTO){
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
            estoque.setQuantidade(estoque.getQuantidade() + estoqueDTO.getQuantidade());
        estoqueRepository.save(estoque);
        return estoqueMapper.mapToDTO(estoque);
    }
    public EstoqueDTO listarProdutoEstoque(Long id){
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        return estoqueMapper.mapToDTO(estoque);

    }
    public List<EstoqueDTO> listarEstoque(){
        List<Estoque> listEstoque = estoqueRepository.findAll();
        List<EstoqueDTO> produtos = listEstoque.stream()
                .map(estoqueMapper::mapToDTO).toList();
        return produtos;
    }
}
