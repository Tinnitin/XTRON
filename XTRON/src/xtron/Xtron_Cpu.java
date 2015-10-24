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
    boolean ciclo;

    public boolean getCiclo() {
        return ciclo;
    }

    public void setCiclo(boolean ciclo) {
        this.ciclo = ciclo;
    }
     
    public Xtron_Cpu() {
        this.memory = new String[99];
        this.acumulator = 0;
        this.instructionCounter = 0;
        this.instructionRegister = "0000";
        this.operateCode = "00";
        this.operand = "00";
        this.ciclo=false;

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
                                numero = JOptionPane.showInputDialog("Ingrese su numero");
                                correcto = validaNumero(numero);
                            }
                            memory[Integer.parseInt(operand)] = numero + "";

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se puede guardar ningun numero en esa pocision");
                        }

                        break;
                    case "11":
                        //  "WRITE";toma una palabra de una ubicacion espesifica y la imprime en pantalla
                        if (memory[Integer.parseInt(operand)] != null) {
                            JOptionPane.showMessageDialog(null, memory[Integer.parseInt(operand)]);

                        } else {
                            throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta mostrar la posicion " + operand + "que es nula");

                        }
                        break;
                    case "20":
                        // "LOAD";toma una palabra de memoria y la colca en el acumulador 
                        if (Integer.parseInt(operand) >= program.length) {

                            if (memory[Integer.parseInt(operand)] != null) {
                                acumulator = Integer.parseInt(memory[Integer.parseInt(operand)]);
                            } else {
                                throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta cargar la posicion " + operand + "que es nula");

                            }

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se puede cargar en el acumuador");
                        }

                        break;
                    case "21":
                        // "STORE";almacena el acumulador en una pocision de memoria
                        if (Integer.parseInt(operand) >= program.length) {

                            memory[Integer.parseInt(operand)] = acumulator + "";

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se puede almacenar el acumulador en esa pocision");

                            ///error de compilacion
                        }
                        break;
                    case "30":
                        //"ADD";suma una pocision de memoria al acumulador y mantiene la suma ahi

                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null) {

                                acumulator = acumulator + Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta sumar la posicion " + operand + "de memoria que es nula");
                            }

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede sumar al  acumulador");
                        }
                        break;
                    case "31":
                        // "SUBTRACT";resta una poscision de memoria al acumulador 

                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null) {
                                acumulator = acumulator - Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta restar la posicion " + operand + "de memoria que es nula");
                            }

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede restar al  acumulador");
                        }                            ///error de compilacion

                        break;
                    case "32":
                        // "DIBIDE";
                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null && Integer.parseInt(memory[Integer.parseInt(operand)]) != 0) {
                                acumulator = acumulator / Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta dividir  la posicion " + operand + "de memoria que es nula o es cero");
                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede dividir  al  acumulador");
                            ///error de compilacion
                        }
                        break;
                    case "33":
                        //"MULTIPLY";
                        if (Integer.parseInt(operand) >= program.length) {
                            if (memory[Integer.parseInt(operand)] != null) {
                                acumulator = acumulator * Integer.parseInt(memory[Integer.parseInt(operand)]);

                            } else {
                                throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta multiplicar   la posicion " + operand + "de memoria que es nula ");

                            }

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede multiplicar al  acumulador");
                        }
                        break;
                    case "40":
                        // "BRANCH";
                        if (Integer.parseInt(operand) < program.length) {

                            instructionCounter = Integer.parseInt(operand) - 1;
                            i = Integer.parseInt(operand) - 1;

                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria no es una instruccion del programa por lo que no se puede ejecutar el BRANCH ");

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

                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria no es una instruccion del programa por lo que no se puede ejecutar el BRANCHNEG ");
                        }
                        break;
                    case "42":
                        //"BRANCHZERO";
                        if (Integer.parseInt(operand) < program.length) {
                            if (acumulator != 0) {
                                instructionCounter = Integer.parseInt(operand) - 1;
                                i = Integer.parseInt(operand) - 1;

                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria no es una instruccion del programa por lo que no se puede ejecutar el BRANCHZERO ");
                            ///error de compilacion
                        }
                        break;
                    case "43":
                        // "HALT";finaliza  el programa
                        return memory;

                    default:
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la instrucion" + operateCode + " no existe en el lenguaje XTRON ");

                }
                if (acumulator < -9999 || acumulator > 9999) {
                    throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + "del programa desborda el acumulador y a provocado un error grave");
                }
                instructionCounter++;
            }
            return memory;

        } else {
            JOptionPane.showMessageDialog(null, "Program Invalid");

        }
        return memory;

    }

    public String[] CPU_Debuger(String[] program, int positionIntruction, int totalIntruction) throws Xtron_Exeption {
        ciclo=false;
        chargeMemory(program);
        instructionCounter = positionIntruction;

        while (instructionCounter <= totalIntruction) {

            instructionRegister = memory[instructionCounter];
            operateCode = instructionRegister.substring(0, 2);
            operand = instructionRegister.substring(2, 4);

            switch (operateCode) {
                case "10"://Read lee una palabra desde el teclado y la guarda en una pocision espesifica
                    String numero = "";
                    boolean correcto;
                    correcto = false;
                    if (Integer.parseInt(operand) >= totalIntruction) {
                        while (correcto == false) {

                            numero = JOptionPane.showInputDialog("Ingrese su numero: ");
                            correcto = validaNumero(numero);
                        }
                        memory[Integer.parseInt(operand)] = numero + "";
                        if (ciclo == false) {
                            return memory;
                        }

                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se puede guardar ningun numero en esa pocision");
                    }
                case "11":
                    //  "WRITE";toma una palabra de una ubicacion espesifica y la imprime en pantalla
                    if (memory[Integer.parseInt(operand)] != null) {
                        JOptionPane.showMessageDialog(null, memory[Integer.parseInt(operand)]);
                        if (ciclo == false) {
                            return memory;
                        }

                    } else {
                        throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta mostrar la posicion " + operand + "que es nula");

                    }

                case "20":
                    // "LOAD";toma una palabra de memoria y la colca en el acumulador 
                    if (Integer.parseInt(operand) >= totalIntruction) {

                        if (memory[Integer.parseInt(operand)] != null) {
                            acumulator = Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta cargar la posicion " + operand + "que es nula");

                        }

                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se puede cargar en el acumuador");
                    }
                case "21":
                    // "STORE";almacena el acumulador en una pocision de memoria
                    if (Integer.parseInt(operand) >= totalIntruction) {

                        memory[Integer.parseInt(operand)] = acumulator + "";
                        if (ciclo == false) {
                            return memory;
                        }
                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se puede almacenar el acumulador en esa pocision");

                        ///error de compilacion
                    }
                case "30":
                    //"ADD";suma una pocision de memoria al acumulador y mantiene la suma ahi

                    if (Integer.parseInt(operand) >= totalIntruction) {
                        if (memory[Integer.parseInt(operand)] != null) {

                            acumulator = acumulator + Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta sumar la posicion " + operand + "de memoria que es nula");
                        }

                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede sumar al  acumulador");
                    }
                    break;
                case "31":
                    // "SUBTRACT";resta una poscision de memoria al acumulador 

                    if (Integer.parseInt(operand) >= program.length) {
                        if (memory[Integer.parseInt(operand)] != null) {
                            acumulator = acumulator - Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta restar la posicion " + operand + "de memoria que es nula");
                        }

                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede restar al  acumulador");
                    }                            ///error de compilacion

                    break;
                case "32":
                    // "DIBIDE";
                    if (Integer.parseInt(operand) >= totalIntruction) {
                        if (memory[Integer.parseInt(operand)] != null && Integer.parseInt(memory[Integer.parseInt(operand)]) != 0) {
                            acumulator = acumulator / Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta dividir  la posicion " + operand + "de memoria que es nula o es cero");
                        }
                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede dividir  al  acumulador");
                        ///error de compilacion
                    }
                    break;
                case "33":
                    //"MULTIPLY";
                    if (Integer.parseInt(operand) >= totalIntruction) {
                        if (memory[Integer.parseInt(operand)] != null) {
                            acumulator = acumulator * Integer.parseInt(memory[Integer.parseInt(operand)]);
                            if (ciclo == false) {
                                return memory;
                            }
                        } else {
                            throw new Xtron_Exeption("Error de compilacion, la linea " + instructionCounter + " genera error ya que intenta multiplicar   la posicion " + operand + "de memoria que es nula ");

                        }

                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria es una instruccion del programa por lo que no se le  puede multiplicar al  acumulador");
                    }
                    break;
                case "40":
                    // "BRANCH";
                      
                    if (Integer.parseInt(operand) <= totalIntruction) {

                        instructionCounter = Integer.parseInt(operand) - 1;
                        ciclo = true;
                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria no es una instruccion del programa por lo que no se puede ejecutar el BRANCH ");

                    }
                    break;
                case "41":
                    // "BRANCHNEG";
                      
                    if (Integer.parseInt(operand) <= totalIntruction) {
                        if (acumulator < 0) {
                            instructionCounter = Integer.parseInt(operand) - 1;
                            ciclo = true;
                        
                        }else {return memory;}
                    } else {

                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria no es una instruccion del programa por lo que no se puede ejecutar el BRANCHNEG ");
                    }
                    break;
                case "42":
                    //"BRANCHZERO";  
                  
                    if (Integer.parseInt(operand) <= totalIntruction) {
                        if (acumulator == 0) {
                            instructionCounter = Integer.parseInt(operand) - 1;
                            ciclo = true;
                        }else {return memory;}
                    } else {
                        throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la pocision" + operand + " de memoria no es una instruccion del programa por lo que no se puede ejecutar el BRANCHZERO ");
                        ///error de compilacion
                    }
                    break;
                case "43":
                    // "HALT";finaliza  el programa
                    return memory;

                default:
                    throw new Xtron_Exeption("Error de compilacion,la linea " + instructionCounter + " genera conflictos ya que la instrucion" + operateCode + " no existe en el lenguaje XTRON ");
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

        return true;
    }

}
