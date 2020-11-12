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
 * @author Admin
 */
public interface TelefoneDao extends BaseDao{
    
    List pesquisarTodo() throws SQLException;
    
    List pesquisarPorTelefone(String telefone) throws SQLException;
}
