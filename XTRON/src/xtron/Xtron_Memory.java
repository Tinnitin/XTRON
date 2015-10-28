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
public class Xtron_Memory {

    String[] memory;// contiene la memoria del Xtron 
    int instructionCounter;//pocision actual de la instruccion 
    String instructionRegister;//pocision de la siguiente instruccion 
    String operateCode;// indica la operacion a realizar
    String operand;// donde guardo la operacion
    int acumulator;
    boolean ciclo;
    boolean terminateProgram;

    public Xtron_Memory() {
        this.memory = new String[100];
        this.acumulator = 0;
        this.instructionCounter = 0;
        this.instructionRegister = "0000";
        this.operateCode = "00";
        this.operand = "00";
        this.ciclo = false;
        this.terminateProgram = false;

    }

    public String[] CPU_Run(String[] program) throws Xtron_Exeption {

        boolean validProgram = validateProgram(program);
        if (validProgram == true) {//el programa es valido
            chargeMemory(program);

            for (int i = 0; i <= program.length - 1; i++) {

                instructionRegister = memory[instructionCounter];
                operateCode = instructionRegister.substring(0, 2);
                operand = instructionRegister.substring(2, 4);

                switch (operateCode) {
                    case "10"://Read lee una palabra desde el teclado y la guarda en una pocision espesifica
                        String numero = "";
                        boolean correcto = false;
                        if (Integer.parseInt(operand) >= program.length) {
                            while (correcto == false) {
                                numero = JOptionPane.showInputDialog("Enter a number");
                                correcto = validaNumero(numero);
                            }
                            memory[Integer.parseInt(operand)] = numero + "";

                        } else {
                            throw new Xtron_Exeption("*** Xtron execution abnormally terminated   ***\n"
                                    + "Line:  " + (instructionCounter + 1) + "   It creates conflicts since the pocision " + operand + ""
                                    + " Memory is a program instruction so you can not save any number in that pocision");
                        }

                        break;
                    case "11":
                        //  "WRITE";toma una palabra de una ubicacion espesifica y la imprime en pantalla
                        if (memory[Integer.parseInt(operand)] != null) {
                            JOptionPane.showMessageDialog(null, memory[Integer.parseInt(operand)]);

                        } else {
                            int var = instructionCounter + 1;
                            throw new Xtron_Exeption("*** Xtron execution abnormally terminated   ***\\n\"line: " + (instructionCounter + 1) + " generates error since attempts to show the position " + operand + "  It is null");

                        }
                        break;
                    case "20":
                        // "LOAD";toma una palabra de memoria y la colca en el acumulador 
                        if (Integer.parseInt(operand) >= program.length) {

                            if (memory[Integer.parseInt(operand)] != null) {
                                acumulator = Integer.parseInt(memory[Integer.parseInt(operand)]);
                            } else {
                                throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to load the position" + operand + "  It is null");

                            }

                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be loaded into the accumulator");
                        }
                        break;
                    case "21":
                        // "STORE";almacena el acumulador en una pocision de memoria
                        if (Integer.parseInt(operand) >= program.length) {

                            memory[Integer.parseInt(operand)] = acumulator + "";

                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is a program instruction so you can not store the accumulator value in that pocision");

                            ///error de compilacion
                        }
                        break;
                    case "30":
                        //"ADD";suma una pocision de memoria al acumulador y mantiene la suma ahi

                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null) {

                                acumulator = acumulator + Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to add the position " + operand + " It is null");
                            }

                        } else { 
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be add into the accumulator");
                        }
                        break;
                    case "31":
                        // "SUBTRACT";resta una poscision de memoria al acumulador 

                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null) {
                                acumulator = acumulator - Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to subtract the position " + operand + " It is null");
                            }

                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be subtract into the accumulator");
                        }                            ///error de compilacion

                        break;
                    case "32":
                        // "DIBIDE";
                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null && Integer.parseInt(memory[Integer.parseInt(operand)]) != 0) {
                                acumulator = acumulator / Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to divide the position " + operand + " It is null or cero");
                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be divide into the accumulator");
                            ///error de compilacion
                        }
                        break;
                    case "33":
                        //"MULTIPLY";
                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null) {
                                acumulator = acumulator * Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to multiply the position " + operand + " It is null ");

                            }

                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be multiply into the accumulator");
                        }
                        break;
                    case "40":
                        // "BRANCH";
                        if (Integer.parseInt(operand) < program.length) {

                            instructionCounter = Integer.parseInt(operand) - 1;
                            i = Integer.parseInt(operand) - 1;

                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is not an instructional program so you can not run the BRANCH ");

                        }
                        break;
                    case "41":
                        // "BRANCHNEG";
                        if (Integer.parseInt(operand) < program.length) {
                            if (acumulator < 0) {
                                instructionCounter = Integer.parseInt(operand) - 1;
                                i = Integer.parseInt(operand) - 1;
                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is not an instructional program so you can not run the BRANCHNEG ");

                        }
                        break;
                    case "42":
                        //"BRANCHZERO";
                        if (Integer.parseInt(operand) < program.length) {
                            if (acumulator == 0) {
                                instructionCounter = Integer.parseInt(operand) - 1;
                                i = Integer.parseInt(operand) - 1;

                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is not an instructional program so you can not run the BRANCHZERO ");
                            ///error de compilacion
                        }
                        break;
                    case "43":
                        // "HALT";finaliza  el programa
                        return memory;

                    default:
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operateCode + "not exist in the language XTRON");

                }
                if (acumulator < -9999 || acumulator > 9999) {
                    throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) +"program and the accumulator overflows caused a serious error");
                }
                instructionCounter++;
            }
            return memory;

        } else {
            JOptionPane.showMessageDialog(null, "Program Invalid");

        }
        return memory;

    }

    public String[] CPU_Debuger(String[] program, int positionIntruction, int totalInstruction) throws Xtron_Exeption {
        ciclo = false;
        chargeMemory(program);
        instructionCounter = positionIntruction;

        while (instructionCounter < totalInstruction) {

            instructionRegister = memory[instructionCounter];
            operateCode = instructionRegister.substring(0, 2);
            operand = instructionRegister.substring(2, 4);

            switch (operateCode) {
                case "10"://Read lee una palabra desde el teclado y la guarda en una pocision espesifica
                    String numero = "";
                    boolean correcto;
                    correcto = false;
                    if (Integer.parseInt(operand) >= totalInstruction) {
                        while (correcto == false) {

                            numero = JOptionPane.showInputDialog("Enter a number: ");
                            correcto = validaNumero(numero);
                        }
                        memory[Integer.parseInt(operand)] = numero + "";
                        if (ciclo == false) {
                            return memory;
                        }
                        break;

                    } else {
                        throw new Xtron_Exeption("*** Xtron execution abnormally terminated   ***\n"
                                + "Line:  " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + ""
                                + " Memory is a program instruction so you can not save any number in that pocision");
                    }
                case "11":
                    //  "WRITE";toma una palabra de una ubicacion espesifica y la imprime en pantalla
                    if (memory[Integer.parseInt(operand)] != null) {
                        JOptionPane.showMessageDialog(null, memory[Integer.parseInt(operand)]);
                        if (ciclo == false) {
                            return memory;
                        }
                        break;

                    } else {
                        throw new Xtron_Exeption("*** Xtron execution abnormally terminated   ***\\n\"" + (instructionCounter + 1) + " generates error since attempts to show the position " + operand + "It is null");
                    }

                case "20":
                    // "LOAD";toma una palabra de memoria y la colca en el acumulador 
                    if (Integer.parseInt(operand) >= totalInstruction) {

                        if (memory[Integer.parseInt(operand)] != null) {
                            acumulator = Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                            break;
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to load the position" + operand + "It is null");

                        }

                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision" + operand + " Memory is an instruction program so it can not be loaded into the accumulator");
                    }
                case "21":
                    // "STORE";almacena el acumulador en una pocision de memoria
                    if (Integer.parseInt(operand) >= totalInstruction) {

                        memory[Integer.parseInt(operand)] = acumulator + "";
                        if (ciclo == false) {
                            return memory;
                        }
                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is a program instruction so you can not store the accumulator value in that pocision");
                        ///error de compilacion
                    }
                    break;
                case "30":
                    //"ADD";suma una pocision de memoria al acumulador y mantiene la suma ahi

                    if (Integer.parseInt(operand) >= totalInstruction) {
                        if (memory[Integer.parseInt(operand)] != null) {

                            acumulator = acumulator + Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to add the position" + operand + "It is null");
                        }

                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be add into the accumulator");
                    }
                    break;
                case "31":
                    // "SUBTRACT";resta una poscision de memoria al acumulador 

                    if (Integer.parseInt(operand) >= totalInstruction) {
                        if (memory[Integer.parseInt(operand)] != null) {
                            acumulator = acumulator - Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to subtract the position" + operand + "It is null");
                        }

                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be subtract into the accumulator");

                    }                            ///error de compilacion

                    break;
                case "32":
                    // "DIBIDE";
                    if (Integer.parseInt(operand) >= totalInstruction) {
                        if (memory[Integer.parseInt(operand)] != null && Integer.parseInt(memory[Integer.parseInt(operand)]) != 0) {
                            acumulator = acumulator / Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to subtract the position " + operand + " It is null");
                        }
                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be divide into the accumulator");
                        ///error de compilacion
                    }
                    break;
                case "33":
                    //"MULTIPLY";
                    if (Integer.parseInt(operand) >= totalInstruction) {
                        if (memory[Integer.parseInt(operand)] != null) {
                            acumulator = acumulator * Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It generates error because you try to multiply the position " + operand + " It is null ");

                        }

                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is an instruction program so it can not be multiply into the accumulator");
                    }
                    break;
                case "40":
                    // "BRANCH";

                    if (Integer.parseInt(operand) <= totalInstruction) {

                        instructionCounter = Integer.parseInt(operand) - 1;
                        ciclo = true;
                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is not an instructional program so you can not run the BRANCH ");

                    }
                    break;
                case "41":
                    // "BRANCHNEG";

                    if (Integer.parseInt(operand) <= totalInstruction) {
                        if (acumulator < 0) {
                            instructionCounter = Integer.parseInt(operand) - 1;
                            ciclo = true;

                        } else if (ciclo == false) {
                            return memory;
                        }
                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is not an instructional program so you can not run the BRANCHNEG ");

                    }
                    break;
                case "42":
                    //"BRANCHZERO";  

                    if (Integer.parseInt(operand) <= totalInstruction) {
                        if (acumulator == 0) {
                            instructionCounter = Integer.parseInt(operand) - 1;
                            ciclo = true;
                        } else if (ciclo == false) {
                            return memory;
                        }
                    } else {
                        throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operand + " Memory is not an instructional program so you can not run the BRANCHZERO ");                        ///error de compilacion
                    }
                    break;
                case "43":
                    // "HALT";finaliza  el programa
                    terminateProgram = true;
                    return memory;

                default:
                    throw new Xtron_Exeption("\"*** Xtron execution abnormally terminated   ***\\\\n\\\"\", Line: " + (instructionCounter + 1) + " It creates conflicts since the pocision " + operateCode + "not exist in the language XTRON");
            }

            instructionCounter++;
        }
        return memory;

    }

    //
    public boolean validateProgram(String[] program) {

        return true;
    }

    //carga la memoria con el nuevo programa 
    private void chargeMemory(String[] program) {

        for (int i = 0; i < program.length; i++) {
            memory[i] = program[i];
        }
    }

    public boolean getTerminateProgram() {
        return terminateProgram;
    }

    public void setTerminateProgram(boolean terminateProgram) {
        this.terminateProgram = terminateProgram;
    }

    public boolean getCiclo() {
        return ciclo;
    }

    public void setCiclo(boolean ciclo) {
        this.ciclo = ciclo;
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

    private boolean validaNumero(String numero) {
        double numero2 = 0;
        boolean bandera = false;

        try {
            numero2 = Double.parseDouble(numero);

            bandera = true;

        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Only use integer numbers please ");
            bandera = false;
            return bandera;
        }

        if (bandera) {
            if (numero2 <= 9999 && numero2 >= -9999) {
                bandera = true;

            } else {
                bandera = false;
                JOptionPane.showMessageDialog(null, "Numbers only allowed between -9999 and 9999");
            }
        }

        return bandera;
    }
}
