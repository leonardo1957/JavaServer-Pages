package entidade;

import java.util.Date;



public class Livro {
    
    private Long id;
    private String nome;
    private String descricao;
    private String anoEdicao;
    private String autor;
    private String editora;

    public Livro() {
    }

    public Livro(Long id, String nome, String descricao, String anoEdicao, String autor, String editora) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.anoEdicao = anoEdicao;
        this.autor = autor;
        this.editora = editora;
    }

    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the anoEdicao
     */
    public String getAnoEdicao() {
        return anoEdicao;
    }

    /**
     * @param anoEdicao the anoEdicao to set
     */
    public void setAnoEdicao(String anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editora
     */
    public String getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

   
    
    
    
    
    
}





/*
*Criar CRUD para Livro 
*Criar uma classe Livro conforme descrição: (nome, descricao, anoEdicao, autor, editora)
*Criar a Daos para a entidade Livro coonforme aulas anteriores
*Criar Teste Unitários para testar o LivroDaoImpl usando padrões de projeto.
*Criar Servlets no controle para o Livro 
*Criar as Jsp para gerenciar o livro conforme aulas anteriores.
*/