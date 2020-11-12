/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.FornecedorDao;
import dao.FornecedorDaoImpl;
import entidade.Fornecedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FornecedorControle", urlPatterns = {"/crud_fornecedor"})
public class FornecedorControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private FornecedorDao fornecedorDao;
    private RequestDispatcher rd;

    protected void processarRequisicao() throws ServletException, IOException {
        String comando = request.getParameter("cmd");

        switch (comando) {
            case "pesquisarPorNome":
                consultarPorNome();
                break;

            case "salvar":
                novo();
                break;

            case "excluir":
                excluir();
                break;

            case "carregar":
                carregarAlterar();
                break;
        }

        rd.forward(request, response);

    }
    
    private void carregarAlterar(){
        Long id = Long.parseLong(request.getParameter("idTela"));
        fornecedorDao = new FornecedorDaoImpl();
        try {
           Fornecedor forn = (Fornecedor) fornecedorDao.pesquisarPorID(id);
           request.setAttribute("fornecedor", forn);
           
        } catch (Exception e) {
             request.setAttribute("msgErro", "erro ao pesquisar por id " + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/novoFornecedor.jsp");
    }
    
    private void excluir(){
        Long id = Long.parseLong(request.getParameter("idTela"));
        fornecedorDao = new FornecedorDaoImpl();
        try {
            fornecedorDao.excluir(id);
            request.setAttribute("msgSucesso", "Excluído com sucesso!");
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao excluir!");
        }
        rd = request.getRequestDispatcher("logado/fornecedor.jsp");
    }

    private void novo() {
        Fornecedor fornecedor = new Fornecedor();
        String id = request.getParameter("id");
        fornecedor.setNome(request.getParameter("nome"));
        fornecedor.setEmail(request.getParameter("email"));
        fornecedor.setTelefone(request.getParameter("telefone"));
        fornecedor.setDescricao(request.getParameter("descricao"));
        try {
            fornecedorDao = new FornecedorDaoImpl();
            if (!id.equals("")) {
                fornecedor.setId(Long.parseLong(id));
                fornecedorDao.alterar(fornecedor);
                request.setAttribute("msgSucesso", "Alterado com sucesso!");
            } else {
                fornecedor.setDataCadastro(new Date());
                fornecedorDao.salvar(fornecedor);
                request.setAttribute("msgSucesso", "Salvo com sucesso!");
            }
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao Salvar!");
        }
        rd = request.getRequestDispatcher("logado/novoFornecedor.jsp");
    }

    private void consultarPorNome() {
        String nome = request.getParameter("nome");
        try {
            fornecedorDao = new FornecedorDaoImpl();
            List<Fornecedor> forns = fornecedorDao.pesquisarPorNome(nome);
            if(forns.isEmpty()){
                request.setAttribute("msgAlerta", "Não foi encontrado nenhum registro com esse valor!");
                request.setAttribute("fornecedores", null);
            }else{
                request.setAttribute("fornecedores", forns);
            }
            
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao pesquisar " + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/fornecedor.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processarRequisicao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processarRequisicao();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
