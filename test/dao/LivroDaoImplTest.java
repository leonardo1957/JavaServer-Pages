/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidade.Livro;
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
public class LivroDaoImplTest{

    private Livro livro;
    private LivroDao livroDao;

    public LivroDaoImplTest() {
        livroDao = new LivroDaoImpl();
    }

   

    @Test
    public void testAlterar() throws SQLException {
        System.out.println("alterar");
        buscarLivroBD();
        livro.setNome("livro alt " + UtilTeste.gerarCaracter(5));
        livroDao.alterar(livro);
        Livro livroAlt = (Livro) livroDao.pesquisarPorID(livro.getId());
        assertEquals(livro.getNome(), livroAlt.getNome());

    }

   // @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarLivroBD();
        livroDao.excluir(livro.getId());
        
        Livro livroExc = (Livro) livroDao.pesquisarPorID(livro.getId());
        assertNull(livroExc);
        
    }

   // @Test
    public void testPesquisarPorID() throws Exception {
        System.out.println("pesquisarPorID");
    }

  //  @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarLivroBD();
        List<Livro> livroPorNome = 
                livroDao.pesquisarPorNome("livro");
        assertTrue(!livroPorNome.isEmpty());
    }

  //  @Test
    public void testPesquisarTodo() throws Exception {
        System.out.println("pesquisarTodo");
        buscarLivroBD();
        List<Livro> livro = livroDao.pesquisarTodo();
        assertTrue(!livro.isEmpty());
    }

    public Livro buscarLivroBD() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;

        String consulta = "SELECT max(id) as id FROM livro";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if (id != 0) {
                livro = (Livro) livroDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception e) {
            System.out.println("Erro no buscarLivroBD " + e.getMessage());
        }
        return livro;
    }



    private void testSalvar() {
           System.out.println("salvar");
           buscarLivroBD();
      
        try {
            livroDao.salvar(livro);
            assertNotNull(livro.getId());
        } catch (Exception ex) {
            fail("Erro ao teste salvar " + ex.getMessage());
        }
    }

}
