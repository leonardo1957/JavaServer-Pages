<%-- 
    Document   : contato
    Created on : 24/08/2020, 10:45:16
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>:.PetShop Senac.:</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Meta Tags - O que são e como utilizá-las">
        <meta name="keywords" content="promoção ração, ração, desenvolvimento">
        <link href="css/estilo.css" rel="stylesheet" />
    </head>
    <body>
        <div id="principal" >
            <div id="banner" class="div_borda">
                <img id="img_banner" src="imagem/banner.jpg" alt="banner petshop..." />
            </div>
            <%@include file="menu.html" %>
            <div id="artigo" class="espaco_topo div_borda">
                <h1>Contato</h1>
                
                <span>${sucesso}</span>
                
                <form id="form_email" method="post" action="site_email">
                    <div class="div_flex">
                        <label>Nome:</label>
                        <input type="text" name="nome" required />
                    </div>
                    <div class="div_flex">
                        <label>E-mail:</label>
                        <input type="text" name="email" />
                    </div>
                    <div class="div_flex">
                        <label>Assunto:</label>
                        <select name="assunto">
                            <option value="financeiro" >Financeiro</option>
                            <option value="suporte">Suporte Técnico</option>
                            <option value="venda">Vendas</option>
                        </select>
                    </div>
                    <div class="div_flex">
                        <label>Mensagem:</label>
                        <textarea id="email_textarea" name="mensagem"></textarea>
                    </div>
                    <div class="div_flex">
                        <input class="botao botao_esquerda" value="Enviar" type="submit" />
                        <input class="botao" type="reset" value="Limpar" />
                    </div>
                </form>

            </div>
            <div id="banner_2" class="div_borda espaco_topo"></div>

            <div id="rodape" class="div_borda espaco_topo"></div>            
        </div>
    </body>
</html>
