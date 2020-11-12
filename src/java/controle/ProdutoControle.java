/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.FabricaConexao;
import dao.FornecedorDaoImpl;
import dao.ProdutoDao;
import dao.ProdutoDaoImpl;
import entidade.Fornecedor;
import entidade.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
@WebServlet(name = "ProdutoControle", urlPatterns = {"/crud_produto"})
public class ProdutoControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ProdutoDao produtoDao;
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

    private void carregarAlterar() {
        Long id = Long.parseLong(request.getParameter("idTela"));
        produtoDao = new ProdutoDaoImpl();
        try {
            Produto produto = (Produto) produtoDao.pesquisarPorID(id);
            request.setAttribute("produto", produto);

        } catch (Exception e) {
            request.setAttribute("msgErro", "erro ao pesquisar por id " + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/novoProduto.jsp");
    }

    private void excluir() {
        Long id = Long.parseLong(request.getParameter("idTela"));
        produtoDao = new ProdutoDaoImpl();
        try {
            produtoDao.excluir(id);
            request.setAttribute("msgSucesso", "Excluído com sucesso!");
        } catch (SQLException e) {
            request.setAttribute("msgErro", "Erro ao excluir!");
        }
        rd = request.getRequestDispatcher("logado/produto.jsp");
    }

    private void novo() {
        Produto produto = new Produto();
        String id = request.getParameter("id");
        produto.setNome(request.getParameter("nome"));
        produto.setDescricao(request.getParameter("descricao"));
        produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
        produto.setPreco(Double.parseDouble(request.getParameter("preco")));
        try {
            produtoDao = new ProdutoDaoImpl();
            if (!id.equals("")) {
                produto.setId(Long.parseLong(id));
                produtoDao.alterar(produto);
                request.setAttribute("msgSucesso", "Alterado com sucesso!");
            } else {
                produto.setCodigo(gerarCodigoProduto());
                produtoDao.salvar(produto);
                request.setAttribute("msgSucesso", "Salvo com sucesso!");
            }
        } catch (SQLException e) {
            request.setAttribute("msgErro", "Erro ao Salvar!");
        }
        rd = request.getRequestDispatcher("logado/novoProduto.jsp");
    }

    private void consultarPorNome() {
        String nome = request.getParameter("nome");
        try {
            produtoDao = new ProdutoDaoImpl();
            List<Produto> produtos = produtoDao.pesquisarPorNome(nome);
            if (produtos.isEmpty()) {
                request.setAttribute("msgAlerta", "Não foi encontrado nenhum registro com esse valor!");
                request.setAttribute("produtos", null);
            } else {
                request.setAttribute("produtos", produtos);
            }
        } catch (SQLException e) {
            request.setAttribute("msgErro", "Erro ao pesquisar " + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/produto.jsp");

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

    private String gerarCodigoProduto() {
        Connection conexao;
        PreparedStatement preparando;
        ResultSet resultSet;
        String codigo = "";
        LocalDate localDateNovo = LocalDate.now();

        String consulta = "SELECT max(id) as id FROM produto";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("id");
            if (id != 0) {
                codigo = (id + 1) + Long.toString(localDateNovo.getYear());
            } else {
                codigo = "1" + localDateNovo.getYear();
            }
        } catch (Exception e) {
            System.out.println("Erro no buscarProdutoBD " + e.getMessage());
        }
        return codigo;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
