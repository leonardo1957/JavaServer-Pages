/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Admin
 */
public class UtilTeste {
    
    public static String gerarCaracter(int numero){
        String letra = "";
        int indice;
        String[] caracter ={"0","1","2","3","4","5","6","7","8","9","a","b","c",
            "d","e","f","g","h","i","j","k","l","m","n","o","p","q","r",
            "s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H",
            "I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        for (int i = 0; i < numero; i++) {
            indice = (int)(Math.random() * caracter.length);
            letra += caracter[indice];
        }
        return letra;
    }
    
    public static String gerarNumero(int qtd){
        String numero = "";
        for (int i = 0; i < qtd; i++) {
            numero += (int)(Math.random() * 10);
        }
        return numero;
    } 
    
    public static String gerarTelefone(){
        return "(48)3" + gerarNumero(3) + "-" +  gerarNumero(4);
    }
    
    public static String gerarTelefoneSemDDD(){
        return "3" + gerarNumero(3) + "-" +  gerarNumero(4);
    }
    
    public static String gerarEmail(){
        return gerarCaracter(10) + "@email.com";
    }
    
    public static void main(String[] args) {
        System.out.println(UtilTeste.gerarTelefone());
    }
}
