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

    String[] memory;// contiene la memoria del Xtron 
    int instructionCounter;//pocision actual de la instruccion 
    String instructionRegister;//pocision de la siguiente instruccion 
    String operateCode;// indica la operacion a realizar
    String operand;// donde guardo la operacion
    int acumulator;

    public Xtron_Cpu() {
        this.memory = new String[99];
        this.memory[4] = "10";
        this.memory[5] = "10";
        this.memory[6]="20";
        this.memory[7]="3";
        this.acumulator = 0;
        this.instructionCounter = 0;
        this.instructionRegister = "0000";
        this.operateCode = "00";
        this.operand = "00";

    }

    public String[] CPU(String[] program) {

        boolean validProgram = validateProgram(program);
        if (validProgram == true) {//el programa es valido
            chargeMemory(program);

            for (int i = 0; i <= program.length - 1; i++) {

                instructionRegister = memory[instructionCounter];
                operateCode = instructionRegister.substring(0, 2);
                operand = instructionRegister.substring(2, 4);

                switch (operateCode) {
                    case "10"://Read lee una palabra desde el teclado y la guarda en una pocision espesifica

                        break;
                    case "11":
                        //  "WRITE";toma una palabra de una ubicacion espesifica y la imprime en pantalla
                        break;
                    case "20":
                        // "LOAD";toma una palabra de memoria y la colca en el acumulador 
                        if (Integer.parseInt(operand) >= program.length) {
                            
                            if(memory[Integer.parseInt(operand)]!=null){
                            acumulator = Integer.parseInt(memory[Integer.parseInt(operand)]);
                            }
                            else{
                            
                            
                            }
                            
                        } else {
                          System.out.print("Error de compilacion intenta cargar una intruccion del programa en el acumulador");
                        }

                        break;
                    case "21":
                        // "STORE";almacena el acumulador en una pocision de memoria
                        if (Integer.parseInt(operand) >= program.length) {

                            memory[Integer.parseInt(operand)] = acumulator + "";
                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "30":
                        //"ADD";suma una pocision de memoria al acumulador y mantiene la suma ahi
                        if (Integer.parseInt(operand) >= program.length) {

                            acumulator = acumulator + Integer.parseInt(memory[Integer.parseInt(operand)]);
                       
                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "31":
                        // "SUBTRACT";resta una poscision de memoria al acumulador 
                        if (Integer.parseInt(operand) >= program.length) {

                            acumulator = acumulator - Integer.parseInt(memory[Integer.parseInt(operand)]);

                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "32":
                        // "DIBIDE";
                        if (Integer.parseInt(operand) >= program.length) {

                            acumulator = acumulator / Integer.parseInt(memory[Integer.parseInt(operand)]);

                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "33":
                        //"MULTIPLY";
                        if (Integer.parseInt(operand) >= program.length) {

                            acumulator = acumulator * Integer.parseInt(memory[Integer.parseInt(operand)]);

                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "40":
                        // "BRANCH";
                        if (Integer.parseInt(operand) < program.length) {

                            instructionCounter = Integer.parseInt(operand);
                            i = Integer.parseInt(operand);

                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "41":
                        // "BRANCHNEG";
                        if (Integer.parseInt(operand) < program.length) {
                            if (acumulator >= 0) {
                                instructionCounter = Integer.parseInt(operand);
                                i = Integer.parseInt(operand);
                            }
                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "42":
                        //"BRANCHZERO";
                        if (Integer.parseInt(operand) < program.length) {
                            if (acumulator != 0) {
                                instructionCounter = Integer.parseInt(operand);
                                i = Integer.parseInt(operand);
                            }
                        } else {

                            ///error de compilacion
                        }
                        break;
                    case "43":
                        // "HALT";finaliza  el programa
                        return memory;

                    default:

                        break;
                }
            instructionCounter++;
            }
            return memory;

        } else {
            JOptionPane.showMessageDialog(null, "Program Invalid");

        }
        return memory;

    }

    //
    public boolean validateProgram(String[] program) {

        //hoadslfjadslfalskfjaslfdsñf
        return true;
    }

    //carga la memoria con el nuevo programa 
    private void chargeMemory(String[] program) {
        for (int i = 0; i < program.length; i++) {
            memory[i] = program[i];
        }
    }

    public String[] getMemory() {
        return memory;
    }

    public void setMemory(String[] memory) {
        this.memory = memory;
    }

    public int getInstructionCounter() {
        return instructionCounter;
    }

    public void setInstructionCounter(int instructionCounter) {
        this.instructionCounter = instructionCounter;
    }

    public String getInstructionRegister() {
        return instructionRegister;
    }

    public void setInstructionRegister(String instructionRegister) {
        this.instructionRegister = instructionRegister;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public int getAcumulator() {
        return acumulator;
    }

    public void setAcumulator(int acumulator) {
        this.acumulator = acumulator;
    }

}
