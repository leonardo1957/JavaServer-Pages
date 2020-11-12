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
public interface BaseDao {
    
    Object salvar(Object object) throws SQLException;
    
    void alterar(Object object) throws SQLException;
    
    void excluir(Long id) throws SQLException;
    
    Object pesquisarPorID(Long id)throws SQLException;    
    
}
