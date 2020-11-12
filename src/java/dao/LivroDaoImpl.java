/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Livro;
import dao.LivroDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class LivroDaoImpl implements LivroDao {
    
    private Connection conexao;
    private PreparedStatement preparando;
    private ResultSet resultSet;

    @Override
    public List pesquisarTodo() throws SQLException {
        String consulta = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();
        Livro livro;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                livro = new Livro(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getString("anoEdicao"), 
                        resultSet.getString("autor"),
                        resultSet.getString("editora")
                        
                );
                livros.add(livro);
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return livros;
    }

    @Override
    public List pesquisarPorNome(String nome) throws SQLException {
          String consulta = "SELECT * FROM livro WHERE nome LIKE ?";
        List<Livro> livros = new ArrayList<>();
        Livro livro;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, "%" + nome + "%");
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {
                livro = new Livro(
                        resultSet.getLong("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getString("anoEdicao"),
                        resultSet.getString("autor"),
                         resultSet.getString("editora")
                        
                );
                livros.add(livro);
            }

        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }

        return livros;
    }

    @Override
    public Object salvar(Object object) throws SQLException {
         Livro livro = (Livro) object;
        String sql = "INSERT INTO livro(nome, descricao, anoEdicao, autor, editora)"
                + " VALUES(?, ?, ?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, livro.getNome());
            preparando.setString(2, livro.getDescricao());
            preparando.setString(3, livro.getAnoEdicao());
            preparando.setString(4, livro.getAutor());
            preparando.setString(5, livro.getEditora());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            livro.setId(resultSet.getLong(1));
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return livro;
    }

    @Override
    public void alterar(Object object) throws SQLException {
        Livro livro = (Livro) object;
        String sql = "UPDATE livro SET nome = ?, descricao = ?, anoEdicao = ?, autor  = ?, editora = ?"
                + "WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, livro.getNome());
            preparando.setString(2, livro.getDescricao());
            preparando.setString(3, livro.getAnoEdicao());
           // preparando.setString(3, livro.getAnoEdicao();
            preparando.setString(4, livro.getAutor());
            preparando.setString (5, livro.getEditora());  
            preparando.setLong(6, livro.getId());
            preparando.executeUpdate();
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    @Override
     public void excluir(Long id) throws SQLException {

        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement("DELETE FROM livro WHERE id = ?");
            preparando.setLong(1, id);
            preparando.executeUpdate();
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
    }

    @Override
   public Object pesquisarPorID(Long id) throws SQLException {
        Livro livro = null;
        String consulta = "SELECT * FROM livro WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setLong(1, id);
            resultSet = preparando.executeQuery();
            if (resultSet.next()) {
                livro = new Livro(
                        id,
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"),
                        resultSet.getString("anoEdicao"),
                        resultSet.getString("autor"),
                        resultSet.getString("editora")
                );
            }
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return livro;
    }
    
}
