/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import xtron.Xtron_Cpu;
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
        program= io.leerTextoArchivo("Prueba.txt");
        
         Xtron_Cpu x=new Xtron_Cpu();
         x.CPU(program);
         System.out.println(x.getAcumulator());
    
    }
}
