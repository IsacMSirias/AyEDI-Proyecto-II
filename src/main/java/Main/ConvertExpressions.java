package Main;

import java.util.Stack;

/**
 *Clase que actua como conversor de expresiones matemáticas de infijo a postifijo.
 *  Verifica si una expresión está correctamente escrita para luego convertirla de forma infija a postfija.
 * @author Ludwin Ramos, Isac Marín
 * @version 1.0
 * @since 08/11/2021
 */

public class ConvertExpressions {
    /**
     * Expresion a evaluar.
     */
    private String expresion;
    /**
     * Pila que se utilizara para evaluar la expresion.
     */
    static Stack<String> pila = new <Character>Stack();

    /**
     * Método encargado de convertir una expresión matemática de infija a postfija
     * @param cadena , parametro que contiene la expresión matemática infija
     * @return retorna una expresión de forma postfija.
     */
    public static String postfijo(String cadena){
        pila.clear();
        String postorderExpression = "";
        char caracterEvaluado;
        boolean temp=true;
        char UltimoCaracter =' ';

        for (int i=0;i<cadena.length();i++){ //se itera cada caracter de la expresión entrante
            caracterEvaluado = cadena.charAt(i);

            // si el caracter es un dígito se introduce directamente a la expresión de salida.
            if (Character.isDigit(caracterEvaluado)) {
                postorderExpression = postorderExpression + Character.toString(caracterEvaluado);
                UltimoCaracter = caracterEvaluado;
                temp=true;
                continue;
            }

            // si el caracter es un parentesis u operador se verifica para agregarlo a una pila
            switch(caracterEvaluado) {
                case ')':
                    // si el caracter es un parentesis que cierra, se remueve un operador de la pila y se
                    //  coloca en la expresión.
                    if (Character.isDigit(UltimoCaracter)){
                        postorderExpression = postorderExpression+" "+ pila.pop();
                        UltimoCaracter = caracterEvaluado;
                        temp=false;
                        break;
                    }
                    postorderExpression = postorderExpression+ pila.pop();
                    UltimoCaracter = caracterEvaluado;
                    temp=false;
                    break;
                default:
                    // si el caracter es un operador se almacena a la pila para ser colocado luego a la expresión.
                    if (!Character.toString(caracterEvaluado).equals("(") & !Character.isDigit(caracterEvaluado)) {
                        pila.push(Character.toString(caracterEvaluado));

                        // En el caso de que un número contenga más de un dígito se separán los numeros en la
                        //  expresión por un espacio para diferenciarlos.
                        if (temp){
                            postorderExpression = postorderExpression +" ";
                        }
                        break;
                    }
                    break;
            }
        }
        // se introduce el operador restante de la pila a la expresión de salida, quien de hecho será el
        // operador que contendrá la raíz del árbol.
        if (Character.isDigit(UltimoCaracter)){
            postorderExpression=postorderExpression +" "+ pila.pop();
            return postorderExpression;
        }
        return postorderExpression;
    }

    /**
     * Método que verifica si la expresión enviada por el ususario está escrita correctamente antes de
     *  convertirla a postfija.
     * @param expression , parametro que contiene la expresión enviada por el usuario.
     * @return retorna un valor booleano "true" si se encontraron fallos de escritura en la expresión y "false"
     * si la expresión no presenta fallos de escritura.
     */
    public boolean verificar(String expression){
        char caracter;
        int parentesis = 0;
        boolean ExpresionCorrecta = true;
        for (int i=0; i<expression.length();i++){
            caracter = expression.charAt(i);
            if (Character.toString(caracter).equals("(") ||Character.toString(caracter).equals(")")){
                parentesis++; // cuenta si la cantidad de parentesis que abren son iguales que los que cierran.
                continue;
            }
            // se verifica si los carácteres que se encuentran en la expresión son válidos
            switch (caracter) {
                case '+':
                    break;
                case '-':
                    break;
                case '*':
                    break;
                case '/':
                    break;
                case '%':
                    break;
                default:
                    if (Character.isDigit(caracter)) {
                        break;
                    }
                    ExpresionCorrecta = false;

                    i = expression.length();
                    break;
            }
        }
        // si la expresión tiene la misma cantidad de paresntesis que abren y que cierra, y la expresión no tiene
        //  carateres distintos a los permitidos se cumplirá y por tanto la expresión esta correctamente escrita.
        if ((parentesis%2)==0 && ExpresionCorrecta){
            return false;
        }
        return true; //si se encontraron fallos de escritura en la expresión matemática
    }
}