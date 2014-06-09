/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package librerias;

import java.util.ArrayList;

/**
 *
 * @author JONATHAN
 */
public class Estadistica {

    private static double variable_A = 0;
    private static double variable_B = 0;
    private static double variable_R = 0;

    public static double DesvSTD(ArrayList arreglo, double y_promedio) {
        double sum = 0.0;
        for (int i = 0; i < arreglo.size(); i++) {
            sum += Math.pow(Double.parseDouble(arreglo.get(i).toString()) - y_promedio, 2); //idem
            //array.length devuelve el número de elementos del array por lo que no es necesario restarle 1.
        }
        return Math.sqrt(sum / (arreglo.size() - 1));
    }

    public static double Promedio(ArrayList arreglo) {
        double sum = 0.0;
        for (int i = 0; i < arreglo.size(); i++) {
            sum += Double.parseDouble(arreglo.get(i).toString());
        }
        return sum / arreglo.size();
    }

    static void Calcular_variables(ArrayList arreglo) {
        //Formula Y=a+bx
        double variableB = 0;
        double var_XY = 0, varY = 0, varX2 = 0, varY2 = 0;
        int varX = 0;
        for (int i = 0; i < arreglo.size(); i++) {
            varY += Double.parseDouble(arreglo.get(i).toString());
            varX += (i + 1);
            var_XY += (i + 1) * Double.parseDouble(arreglo.get(i).toString());
            varX2 += Math.pow((i + 1), 2);
            varY2 += Math.pow(Double.parseDouble(arreglo.get(i).toString()), 2);
        }
        variableB = (arreglo.size() * var_XY - varX * varY) / (arreglo.size() * varX2 - Math.pow(varX, 2));
        setVariable_B(variableB);

        double variableA = (varY - variableB * varX) / (arreglo.size());
        setVariable_A(variableA);

        double variableR = (arreglo.size() * var_XY - varX * varY) / (Math.sqrt(arreglo.size() * varX2 - Math.pow(varX, 2)) * Math.sqrt(arreglo.size() * varY2 - Math.pow(varY, 2)));
        setVariable_R(variableR);
    }

    public static Double VariableB(ArrayList arreglo) {
        Calcular_variables(arreglo);
        return getVariable_B();
    }

    public static Double VariableR() {
        return getVariable_R();
    }

    public static Double VariableA() {
        return getVariable_A();
    }

    /**
     * @return the variablea
     */
    public static double getVariable_A() {
        return variable_A;
    }

    /**
     * @param variablea the variablea to set
     */
    public static void setVariable_A(double variablea) {
        Estadistica.variable_A = variablea;
    }

    /**
     * @return the variable_B
     */
    public static double getVariable_B() {
        return variable_B;
    }

    /**
     * @param aVariable_B the variable_B to set
     */
    public static void setVariable_B(double aVariable_B) {
        variable_B = aVariable_B;
    }

    /**
     * @return the variable_R
     */
    public static double getVariable_R() {
        return variable_R;
    }

    /**
     * @param aVariable_R the variable_R to set
     */
    public static void setVariable_R(double aVariable_R) {
        variable_R = aVariable_R;
    }
}
