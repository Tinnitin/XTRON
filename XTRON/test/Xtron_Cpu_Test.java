/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import xtron.Xtron_Memory;
import xtron.Xtron_Exeption;
import xtron.Xtron_IO;

/**
 *
 * @author Tin
 */
public class Xtron_Cpu_Test {
    
    public Xtron_Cpu_Test() {
    }
   
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
    public void hello() {
         Xtron_IO io= new Xtron_IO();
         String program[];
        program= io.leerTextoArchivo("debug.txt");
        
        
         Xtron_Memory cpu=new Xtron_Memory();
        
         for (int i = 0; i < program.length-1; i++) {
             try {
<<<<<<< HEAD
                 program=cpu.CPU_Debuger(program, i,7);
=======
                 program=cpu.CPU_Debuger(program, i,8);
>>>>>>> 224112ee734a3b0246dfd54c3f3c42fa9a3137da
                 System.out.print(cpu.getAcumulator());
                 
//                 for (String re : program) {
//                     System.out.println(re);
//                 }
//                
                 
             } catch (Xtron_Exeption ex) {
                 System.out.println(ex.getMessage());
             }
             
         }
 
      
    
    }
}
