/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import entidade.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilTeste;

/**
 *
 * @author Admin
 */
public class TelefoneDaoImplTest {

    private TelefoneDao telefoneDao;
    private Telefone telefone;

    public TelefoneDaoImplTest() {
        telefoneDao = new TelefoneDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        telefone = new Telefone(null, UtilTeste.gerarTelefoneSemDDD(),
                "48",
                "Operadora " + UtilTeste.gerarCaracter(5),
                "Contato " + UtilTeste.gerarCaracter(7)
        );
        telefoneDao.salvar(telefone);
        assertNotNull(telefone.getId());
    }

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarTelefoneBD();
        telefone.setNumero(UtilTeste.gerarTelefoneSemDDD());
        telefone.setOperadora("Operadora " + UtilTeste.gerarCaracter(5));
        telefoneDao.alterar(telefone);
        Telefone telAlterado = (Telefone) telefoneDao.pesquisarPorID(telefone.getId());
        assertEquals(telefone.getNumero(), telAlterado.getNumero());
        assertEquals(telefone.getOperadora(), telAlterado.getOperadora());
    }

    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarTelefoneBD();
        telefoneDao.excluir(telefone.getId());
        Telefone telExcluido = (Telefone) telefoneDao.pesquisarPorID(telefone.getId());
        assertNull(telExcluido);
    }

    @Test
    public void testPesquisarPorID() throws Exception {
        System.out.println("pesquisarPorID");
        Long id = null;
    }

    @Test
    public void testPesquisarTodo() throws Exception {
        System.out.println("pesquisarTodo");
        buscarTelefoneBD();
        List<Telefone> telefones = telefoneDao.pesquisarTodo();
        assertTrue(!telefones.isEmpty());
    }

    @Test
    public void testPesquisarPorTelefone() throws Exception {
        System.out.println("pesquisarPorTelefone");
        buscarTelefoneBD();
        List<Telefone> telefones = telefoneDao.pesquisarPorTelefone(telefone.getNumero());
        assertTrue(!telefones.isEmpty());
    }

    public Telefone buscarTelefoneBD() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;

        String consulta = "SELECT max(id) as id FROM telefone";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if (id != 0) {
                telefone = (Telefone) telefoneDao.pesquisarPorID(id);
            } else {
                testSalvar();
            }
        } catch (Exception e) {
            System.out.println("Erro no buscarTelefoneBD " + e.getMessage());
        }
        return telefone;
    }

}
