<%-- 
    Document   : contato
    Created on : 24/08/2020, 10:45:16
    Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>:.PetShop Senac.:</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Meta Tags - O que são e como utilizá-las">
        <meta name="keywords" content="promoção ração, ração, desenvolvimento">
        <link href="/SitePetshop/css/estilo.css" rel="stylesheet" />
    </head>
    <body>
        <div id="principal" >
            <div id="banner" class="div_borda">
                <img id="img_banner" src="/SitePetshop/imagem/banner.jpg" 
                     alt="banner petshop..." />
            </div>

            <%@include file="menu.html" %>

            <div id="menu_lateral" class="div_borda espaco_topo">
                <ul>
                    <li>
                        <a href="/SitePetshop/logado/produto.jsp">Pesquisa</a>
                    </li> 
                    <li>
                        <a href="/SitePetshop/logado/novoProduto.jsp">Novo</a>
                    </li> 
                </ul>
            </div>

            <div id="sistema" class="espaco_topo div_borda">
                <h1>Produto</h1>
                
                <c:if test="${msgSucesso != null}">
                    <p class="mensagemSucesso">${msgSucesso}</p>
                </c:if>
                
                <c:if test="${msgErro != null}">
                    <p class="mensagemErro">${msgErro}</p>
                </c:if>
                    

                <form id="form_email" method="post" action="/SitePetshop/crud_produto?cmd=salvar" >
                    <input type="hidden" name="id" value="${produto.id}" />
                    <div class="div_flex">
                        <label>Nome:</label>
                        <input type="text" name="nome" required value="${produto.nome}" />                        
                    </div>
                    <div class="div_flex">
                        <label>Estoque:</label>
                        <input type="text" name="estoque" required value="${produto.estoque}" />                        
                    </div>
                    <div class="div_flex">
                        <label>Preço:</label>
                        <input type="text" name="preco" required value="${produto.preco}" />                        
                    </div>
                    <div class="div_flex">
                        <label>Descrição:</label>
                        <textarea id="email_textarea" name="descricao">${produto.descricao}</textarea>
                    </div>
                    <div class="div_flex">
                        <input class="botao botao_esquerda" value="Enviar" type="submit" />
                        <input class="botao" type="reset" value="Limpar" />
                    </div>

                </form>

            </div>


            <div id="rodape" class="div_borda espaco_topo"></div>            
        </div>
    </body>
</html>
