/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.oracle.jrockit.jfr.Producer;
import entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilTeste;

/**
 *
 * @author Admin
 */
public class ProdutoDaoImplTest {

    private Produto produto;
    private ProdutoDao produtoDao;

    public ProdutoDaoImplTest() {
        produtoDao = new ProdutoDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        produto = new Produto(null, UtilTeste.gerarCaracter(10), "bla, bla...",
                Double.parseDouble(UtilTeste.gerarNumero(4)),
                Integer.parseInt(UtilTeste.gerarNumero(2)), gerarCodigoProduto());
        produtoDao.salvar(produto);
        assertNotNull(produto.getId());

    }

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarProdutoBD();
        produto.setNome("Produto alterado " + UtilTeste.gerarCaracter(5));
        produtoDao.alterar(produto);
        Produto prodAlterado = (Produto) produtoDao.pesquisarPorID(produto.getId());
        assertEquals(produto.getNome(), prodAlterado.getNome());
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarProdutoBD();
        produtoDao.excluir(produto.getId());
        Produto prodExcluido = (Produto) produtoDao.pesquisarPorID(produto.getId());
        assertNull(prodExcluido);
    }

//    @Test
    public void testPesquisarPorID() throws Exception {
        System.out.println("pesquisarPorID");
        Long id = null;
        ProdutoDaoImpl instance = new ProdutoDaoImpl();
        Object expResult = null;
        Object result = instance.pesquisarPorID(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarProdutoBD();
        List<Produto> produtos = produtoDao.pesquisarPorNome(produto.getNome());
        assertTrue(!produtos.isEmpty());
    }

    @Test
    public void testPesquisarTodo() throws Exception {
        System.out.println("pesquisarTodo");
        buscarProdutoBD();
        List<Produto> produtos = produtoDao.pesquisarTodo();
        assertTrue(!produtos.isEmpty());
    }

    public Produto buscarProdutoBD() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;

        String consulta = "SELECT max(id) as id FROM produto";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if (id != 0) {
                produto = (Produto) produtoDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception e) {
            System.out.println("Erro no buscarProdutoBD " + e.getMessage());
        }
        return produto;
    }

    public String gerarCodigoProduto() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;
        String codigo = "";
        LocalDate localDateNovo = LocalDate.now();

        String consulta = "SELECT max(id) as id FROM produto";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if (id != 0) {
                codigo = (id + 1) + Long.toString(localDateNovo.getYear());
            } else {
                codigo = "1" + localDateNovo.getYear();
            }
        } catch (Exception e) {
            System.out.println("Erro no buscarProdutoBD " + e.getMessage());
        }
        return codigo;
    }

}
