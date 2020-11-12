/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Formulario", urlPatterns = {"/testeFormulario"})
public class Formulario extends HttpServlet {

    
    protected void processarRequisicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("E-mail: " + request.getParameter("email"));
        System.out.println("Senha: " + request.getParameter("senha"));
        System.out.println("Cadastro: " + request.getParameter("cadastro"));
        System.out.println("Sexo: " + request.getParameter("sexo"));
        System.out.println("Linguagens Conhecidas");
        
        String[] nomes = request.getParameterValues("linguagens");
        for (String linguagem : nomes) {
            System.out.println(linguagem);
        }
        
        response.sendRedirect("formulario.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processarRequisicao(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processarRequisicao(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
