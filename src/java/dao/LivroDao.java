/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface LivroDao extends BaseDao{
    
    List pesquisarTodo() throws SQLException;
    
    List pesquisarPorNome(String nome) throws SQLException;
    
}
