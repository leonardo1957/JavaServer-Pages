/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Fornecedor;
import entidade.Telefone;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TelefoneDaoImpl implements TelefoneDao {

    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

    @Override
    public Object salvar(Object object) throws SQLException {
        Telefone telefone = (Telefone) object;
        String sql = "INSERT INTO telefone(numero, ddd, operadora, contato)"
                + " VALUES(?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, telefone.getNumero());
            preparando.setString(2, telefone.getDdd());
            preparando.setString(3, telefone.getOperadora());
            preparando.setString(4, telefone.getContato());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            telefone.setId(resultSet.getLong(1));
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return telefone;
    }

    @Override
    public void alterar(Object object) throws SQLException {
        Telefone telefone = (Telefone) object;
        String sql = "UPDATE telefone SET numero = ?, ddd = ?, operadora = ?, contato  = ? "
                + "WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, telefone.getNumero());
            preparando.setString(2, telefone.getDdd());
            preparando.setString(3, telefone.getOperadora());
            preparando.setString(4, telefone.getContato());
            preparando.setLong(5, telefone.getId());
            preparando.executeUpdate();
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    @Override
    public void excluir(Long id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM telefone WHERE id = ?");
            preparando.setLong(1, id);
            preparando.executeUpdate();
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    @Override
    public Object pesquisarPorID(Long id) throws SQLException {
        Telefone telefone = null;
        String consulta = "SELECT * FROM telefone WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setLong(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                telefone = new Telefone(
                        id,
                        resultSet.getString("numero"),
                        resultSet.getString("ddd"),
                        resultSet.getString("operadora"),
                        resultSet.getString("contato")
                );
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return telefone;
    }

    @Override
    public List pesquisarTodo() throws SQLException {
        String consulta = "SELECT * FROM telefone";
        List<Telefone> telefones = new ArrayList<>();
        Telefone telefone;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                telefone = new Telefone(
                        resultSet.getLong("id"),
                        resultSet.getString("numero"),
                        resultSet.getString("ddd"),
                        resultSet.getString("operadora"),
                        resultSet.getString("contato")
                );
                telefones.add(telefone);
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return telefones;
    }

    @Override
    public List pesquisarPorTelefone(String numero) throws SQLException {
        String consulta = "SELECT * FROM telefone WHERE numero = ?";
        List<Telefone> telefones = new ArrayList<>();
        Telefone telefone;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, numero);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                telefone = new Telefone(
                        resultSet.getLong("id"),
                        numero,
                        resultSet.getString("ddd"),
                        resultSet.getString("operadora"),
                        resultSet.getString("contato")
                );
                telefones.add(telefone);
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return telefones;
    }

}
