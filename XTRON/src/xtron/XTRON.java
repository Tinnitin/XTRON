/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtron;


/**
 *
 * @author Tin
 */
public class XTRON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Xtron_IO archivo = new Xtron_IO();
    String var="";
    String vec[]=archivo.leerTextoArchivo("Prueba.txt");
        for (int i = 0; i < vec.length; i++) {
            var+= "+"+vec[i]+"\n";
            
        }
        
        System.out.println(var);
    
   
    }
    
}
