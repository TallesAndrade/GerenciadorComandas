package com.talles.GerenciadorComandas.service;

import com.talles.GerenciadorComandas.controller.dtos.ProdutoRequestDTO;
import com.talles.GerenciadorComandas.controller.dtos.ProdutoResponseDTO;
import com.talles.GerenciadorComandas.entity.Produto;
import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import com.talles.GerenciadorComandas.exceptions.ProdutoNotFoundException;
import com.talles.GerenciadorComandas.mapper.ProdutoMapper;
import com.talles.GerenciadorComandas.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {


    public static final String NOME = "Fanta";
    public static final BigDecimal PRECO = new BigDecimal("10.5");
    public static final String CODIGO_DE_BARRAS = "17236712637812738";
    public static final List<ProdutoComanda> PRODUTO_COMANDAS_VAZIO = Collections.emptyList();
    public static final boolean ATIVO = true;

    public static final long ID = 1L;
    public static final String NOME1 = "Coca";
    public static final long ID1 = 2L;
    public static final String CODIGO_DE_BARRAS1 = null;
    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @Mock
    private EstoqueService estoqueService;

    @InjectMocks
    private ProdutoService service;

    private ProdutoRequestDTO produtoRequestDTO;
    private Produto produto;
    private Produto produtoComId;
    private ProdutoResponseDTO produtoResponseDTO;

    private ProdutoRequestDTO produtoRequestSemCodigoDeBarras;
    private Produto produtoSemCodigoDeBarras;
    private Produto produtoComIdSemCodigoDeBarras;
    private ProdutoResponseDTO produtoResponseSemCodigoDeBarras;


    @BeforeEach
    void setUp() {
        criarProduto();
    }

    @Test
    @DisplayName("Deve criar um produto e um estoque com sucesso quando recebido os parametros corretos")
    void deveCriarUmProdutoUmEstoqueComSucesso_QuandoRecebidoParametrosCorretos(){

        when(produtoMapper.toEntity(produtoRequestDTO)).thenReturn(produto);
        when(produtoRepository.save(produto)).thenReturn(produtoComId);
        when(produtoMapper.toDTO(produtoComId)).thenReturn(produtoResponseDTO);

        ProdutoResponseDTO resposta = service.cadastrarProduto(produtoRequestDTO);

        verify(produtoRepository,times(1)).save(produto);
        verify(estoqueService,times(1)).criarEstoque(produtoComId);
        
        assertNotNull(resposta);
        assertEquals(ID, resposta.getId());
        assertEquals(NOME, resposta.getNome());
        assertEquals(PRECO, resposta.getPreco());
        assertEquals(CODIGO_DE_BARRAS, resposta.getCodigoDeBarras());
        assertEquals(ATIVO, resposta.isAtivo());

    }

    @Test
    void deveCriarUmProdutoUmEstoqueComSucesso_QuandoRecebidoParametrosCorretosCodigoDeBarrasNull() {

        when(produtoMapper.toEntity(produtoRequestSemCodigoDeBarras)).thenReturn(produtoSemCodigoDeBarras);
        when(produtoRepository.save(produtoSemCodigoDeBarras)).thenReturn(produtoComIdSemCodigoDeBarras);
        when(produtoMapper.toDTO(produtoComIdSemCodigoDeBarras)).thenReturn(produtoResponseSemCodigoDeBarras);

        ProdutoResponseDTO resposta = service.cadastrarProduto(produtoRequestSemCodigoDeBarras);

        verify(produtoMapper,times(1)).toEntity(produtoRequestSemCodigoDeBarras);
        verify(produtoRepository,times(1)).save(produtoSemCodigoDeBarras);
        verify(estoqueService,times(1)).criarEstoque(produtoComIdSemCodigoDeBarras);
        verify(produtoMapper,times(1)).toDTO(produtoComIdSemCodigoDeBarras);

        assertNotNull(resposta);
        assertEquals(ID1, resposta.getId());
        assertEquals(NOME1, resposta.getNome());
        assertEquals(PRECO, resposta.getPreco());
        assertNull(resposta.getCodigoDeBarras());
        assertEquals(ATIVO,resposta.isAtivo());

        

    }

    @Test
    void deveListarTodosOsProdutoAtivados(){
        List<Produto> produtos = List.of(produtoComId, produtoComIdSemCodigoDeBarras);

        when(produtoRepository.findByAtivoTrue()).thenReturn(produtos);
        when(produtoMapper.toDTO(produtos.get(0))).thenReturn(produtoResponseDTO);
        when(produtoMapper.toDTO(produtos.get(1))).thenReturn(produtoResponseSemCodigoDeBarras);

        List<ProdutoResponseDTO> resposta = service.listarProdutos();

        verify(produtoRepository,times(1)).findByAtivoTrue();
        verify(produtoMapper,times(1)).toDTO(produtos.get(0));
        verify(produtoMapper,times(1)).toDTO(produtos.get(1));

        assertNotNull(resposta);
        assertEquals(2,resposta.size());
        assertEquals(ID, resposta.get(0).getId());
        assertEquals(NOME, resposta.get(0).getNome());
        assertEquals(PRECO, resposta.get(0).getPreco());
        assertEquals(CODIGO_DE_BARRAS, resposta.get(0).getCodigoDeBarras());
        assertEquals(ATIVO,resposta.get(0).isAtivo());

        assertEquals(ID1, resposta.get(1).getId());
        assertEquals(NOME1, resposta.get(1).getNome());
        assertEquals(PRECO, resposta.get(1).getPreco());
        assertNull(resposta.get(1).getCodigoDeBarras());
        assertEquals(ATIVO,resposta.get(1).isAtivo());
    }

    @Test
    void deveRetornarUmaListaVaziaCasoNaoTenhaProdutosCadastrados(){
        when(produtoRepository.findByAtivoTrue()).thenReturn(Collections.emptyList());

        List<ProdutoResponseDTO> resposta = service.listarProdutos();

        verify(produtoRepository,times(1)).findByAtivoTrue();
        assertNotNull(resposta);
        assertTrue(resposta.isEmpty());
    }

    @Test
    void deveRetornarUmProdutoComSucessoAtivoNoDb(){
        Optional<Produto> produtoOptional = Optional.of(produtoComId);
        when(produtoRepository.findByIdAndAtivoTrue(ID)).thenReturn(produtoOptional);
        when(produtoMapper.toDTO(produtoOptional.get())).thenReturn(produtoResponseDTO);

        ProdutoResponseDTO resposta = service.produtoById(ID);

        verify(produtoRepository,times(1)).findByIdAndAtivoTrue(ID);
        verify(produtoMapper,times(1)).toDTO(produtoOptional.get());

        assertNotNull(resposta);
        assertEquals(ID, resposta.getId());
        assertEquals(NOME, resposta.getNome());
        assertEquals(PRECO, resposta.getPreco());
        assertEquals(CODIGO_DE_BARRAS, resposta.getCodigoDeBarras());
        assertEquals(ATIVO,resposta.isAtivo());

    }

    @Test
    void deveRetornarUmOptionalVazioDoDbLancarException(){
        when(produtoRepository.findByIdAndAtivoTrue(99L)).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = assertThrows(ProdutoNotFoundException.class,
                () -> service.produtoById(99L));

    }


    private void criarProduto(){
        produtoRequestDTO = new ProdutoRequestDTO(NOME, PRECO, CODIGO_DE_BARRAS);
        produto = new Produto(null, NOME, PRECO, CODIGO_DE_BARRAS, PRODUTO_COMANDAS_VAZIO, ATIVO);
        produtoComId = new Produto(ID, NOME, PRECO, CODIGO_DE_BARRAS, PRODUTO_COMANDAS_VAZIO, ATIVO);
        produtoResponseDTO = new ProdutoResponseDTO(ID, NOME, PRECO, CODIGO_DE_BARRAS, ATIVO);

        produtoRequestSemCodigoDeBarras = new ProdutoRequestDTO(NOME1, PRECO, null);
        produtoSemCodigoDeBarras = new Produto(null, NOME1, PRECO, CODIGO_DE_BARRAS1, PRODUTO_COMANDAS_VAZIO, ATIVO);
        produtoComIdSemCodigoDeBarras = new Produto(ID1, NOME1, PRECO, CODIGO_DE_BARRAS1, PRODUTO_COMANDAS_VAZIO, ATIVO);
        produtoResponseSemCodigoDeBarras = new ProdutoResponseDTO(ID1, NOME1, PRECO, CODIGO_DE_BARRAS1, ATIVO);
    }
}