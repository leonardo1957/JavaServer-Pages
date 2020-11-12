/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;


import dao.LivroDao;
import dao.LivroDaoImpl;
import entidade.Livro;
import java.io.IOException;
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
 * @author User
 */
@WebServlet(name = "livroControle", urlPatterns = {"/crud_livro"})
public class LivroControle extends HttpServlet{
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private LivroDao LivroDao;
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
        LivroDao = new LivroDaoImpl();
        try {
           Livro livro = (Livro) LivroDao.pesquisarPorID(id);
           request.setAttribute("livro", livro);
           
        } catch (Exception e) {
             request.setAttribute("msgErro", "erro ao pesquisar por id " + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/novoLivro.jsp");
    }
    
    private void excluir(){
        Long id = Long.parseLong(request.getParameter("idTela"));
        LivroDao = new LivroDaoImpl();
        try {
            LivroDao.excluir(id);
            request.setAttribute("msgSucesso", "Excluído com sucesso!");
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao excluir!");
        }
        rd = request.getRequestDispatcher("logado/livro.jsp");
    }

    private void novo() {
        Livro livro = new Livro();
        String id = request.getParameter("id");
        livro.setNome(request.getParameter("nome"));
        livro.setDescricao(request.getParameter("descricao"));
        livro.setAnoEdicao(request.getParameter("anoEdicao"));
        livro.setAutor(request.getParameter("autor"));
        livro.setEditora(request.getParameter("editora"));
        try {
            LivroDao = new LivroDaoImpl();
            if (!id.equals("")) {
                livro.setId(Long.parseLong(id));
                LivroDao.alterar(livro);
                request.setAttribute("msgSucesso", "Alterado com sucesso!");
            } else {                
                LivroDao.salvar(livro);
                request.setAttribute("msgSucesso", "Salvo com sucesso!");
            }
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao Salvar!");
        }
        rd = request.getRequestDispatcher("logado/novoLivro.jsp");
    }

    private void consultarPorNome() {
        String nome = request.getParameter("nome");
        try {
            LivroDao = new LivroDaoImpl();
            List<Livro> livro = LivroDao.pesquisarPorNome(nome);
            if(livro.isEmpty()){
                request.setAttribute("msgAlerta", "Não foi encontrado nenhum registro com esse valor!");
                request.setAttribute("livros", null);
            }else{
                request.setAttribute("livros", livro);
            }
            
        } catch (Exception e) {
            request.setAttribute("msgErro", "Erro ao pesquisar " + e.getMessage());
        }
        rd = request.getRequestDispatcher("logado/livro.jsp");

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
