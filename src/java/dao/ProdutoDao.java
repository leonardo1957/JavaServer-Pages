/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ProdutoDao extends BaseDao{
    
    List<Produto> pesquisarPorNome(String nome)throws SQLException;
    
    List<Produto> pesquisarTodo()throws SQLException;
}
