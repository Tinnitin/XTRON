/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtron;

import javax.swing.JOptionPane;

/**
 *
 * @author Tin
 */
public class Xtron_Cpu {
 String [] memory;// contiene la memoria del Xtron 
 int instructionCounter;//pocision actual de la instruccion 
 String instructionRegister;//pocision de la siguiente instruccion 
 String operateCode;// indica la operacion a realizar
 String operand;// donde guardo la operacion
 int acumulator;
    public Xtron_Cpu() {
       this.memory = new String[99];
       this.acumulator=0;
       this.instructionCounter=0;
       this.instructionRegister="0000";
       this.operateCode="00";
       this.operand="00";   
      
    }
    public void CPU(String []program){
        
    boolean validProgram=validateProgram(program);
    if(validProgram==true){//el programa es valido
       chargeMemory(program);
       
        for (int i = 0; i <program.length; i++) {
            
           instructionRegister=memory[instructionCounter];
           operateCode=instructionRegister.substring(1, 3);
           operand=instructionRegister.substring(3,5);
           
           switch(operateCode)
        {
            case "10"://"READ una variable y la guarda en operand";
               
                
                break;
            case "11":
              //  "WRITE";
                break;
            case "20":
               // "LOAD";
            if(Integer.parseInt(operand)>program.length){
          
                acumulator= Integer.parseInt(memory[Integer.parseInt(operand)])  ;
            }
            else{
            ///error de compilacion
            }
                
                break;
            case "21":
               // "STORE";
                if(Integer.parseInt(operand)>program.length){
          
                memory[Integer.parseInt(operand)]=acumulator+"";
            }
                else{
                    
                 ///error de compilacion
               }
                 break;
            case "30":
                //"ADD";
                   if(Integer.parseInt(operand)>program.length){
          
                   acumulator= acumulator+Integer.parseInt(memory[Integer.parseInt(operand)]);
            }
                else{
                    
                 ///error de compilacion
               }
                break;
            case "31":
             // "SUBTRACT";
                if(Integer.parseInt(operand)>program.length){
          
                  
            }
                else{
                    
                 ///error de compilacion
               }
                break;
            case "32":
               // "DIBIDE";
                break;
            case "33":
                //"MULTIPLY";
                break;
            case "40":
               // "BRANCH";
                break;
            case "41":
               // "BRANCHNEG";
                break;
            case "42":
               //"BRANCHZERO";
                break;
            case "43":
           // "HALT";
                break;
            default:
               
                break;
        }
            
        }
       
    }
    else{
        JOptionPane.showMessageDialog(null,"Program Invalid");
    
    }
    
    }
    //
    public boolean validateProgram(String []program){
    
    
        //hoadslfjadslfalskfjaslfdsñf
        return true;
    }
    //carga la memoria con el nuevo programa 
    private void  chargeMemory(String[] program) {
        for (int i = 0; i < program.length; i++) {
            memory[i]=program[i];
        }
    }
   
    
}
