/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import util.UtilTeste;

/**
 *
 * @author Admin
 */
public class FornecedorDaoImplTest {

    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;

    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        fornecedor = new Fornecedor(
                null,
                "fornecedor " + UtilTeste.gerarCaracter(5),
                UtilTeste.gerarEmail(),
                UtilTeste.gerarTelefone(),
                "bla, bla, bla...",
                new Date());
        try {
            fornecedorDao.salvar(fornecedor);
            assertNotNull(fornecedor.getId());
        } catch (Exception ex) {
            fail("Erro ao teste salvar " + ex.getMessage());
        }
    }

   // @Test
    public void testAlterar() throws SQLException {
        System.out.println("alterar");
        buscarFornecedorBD();
        fornecedor.setNome("fornecedor alt " + UtilTeste.gerarCaracter(5));
        fornecedorDao.alterar(fornecedor);
        Fornecedor fornecedorAlt = (Fornecedor) fornecedorDao.pesquisarPorID(fornecedor.getId());
        assertEquals(fornecedor.getNome(), fornecedorAlt.getNome());

    }

   // @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarFornecedorBD();
        fornecedorDao.excluir(fornecedor.getId());
        
        Fornecedor fornecedorExc = (Fornecedor) fornecedorDao.pesquisarPorID(fornecedor.getId());
        assertNull(fornecedorExc);
        
    }

   // @Test
    public void testPesquisarPorID() throws Exception {
        System.out.println("pesquisarPorID");
    }

   // @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarFornecedorBD();
        List<Fornecedor> forncedorPorNome = 
                fornecedorDao.pesquisarPorNome("fornecedor");
        assertTrue(!forncedorPorNome.isEmpty());
    }

   // @Test
    public void testPesquisarTodo() throws Exception {
        System.out.println("pesquisarTodo");
        buscarFornecedorBD();
        List<Fornecedor> fornecedores = fornecedorDao.pesquisarTodo();
        assertTrue(!fornecedores.isEmpty());
    }

    public Fornecedor buscarFornecedorBD() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;

        String consulta = "SELECT max(id) as id FROM fornecedor";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if (id != 0) {
                fornecedor = (Fornecedor) fornecedorDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception e) {
            System.out.println("Erro no buscarFornecedorBD " + e.getMessage());
        }
        return fornecedor;
    }

}
