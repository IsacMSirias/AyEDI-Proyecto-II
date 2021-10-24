package ExpressionTree;

import java.util.Stack;

public class ConvertExpressions {
    private String expresion;
    Stack<String> pila = new <Character>Stack();

    // recibe como argumento la expresión infija para convertirla a postfija
    public String postfijo(String cadena){
        pila.clear();
        String postorderExpression = "";
        char caracterEvaluado;
        boolean temp=true;

        for (int i=0;i<cadena.length();i++){ //se itera cada caracter de la expresión entrante
            caracterEvaluado = cadena.charAt(i);

            // si el caracter es un dígito se introduce directamente a la expresión de salida.
            if (Character.isDigit(caracterEvaluado)) {
                postorderExpression = postorderExpression + Character.toString(caracterEvaluado);
                temp=true;
                continue;
            }

            // si el caracter es un parentesis u operador se verifica para agregarlo a una pila
            switch(caracterEvaluado) {
                case ')':
                    // si el caracter es un parentesis que cierra, se remueve un operador de la pila y se
                    //  coloca en la expresión.
                    postorderExpression = postorderExpression+" "+ pila.pop();
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
        postorderExpression=postorderExpression + pila.pop();
        return postorderExpression;
    }

    // método que verifica si la expresión de entrada está correctamente escrita antes de convertirla a postfija.
    public boolean verificar(String expression){
        char caracter;
        int parentesis = 0;
        boolean ExpresionCorrecta = true;
        for (int i=0; i<expression.length();i++){
            caracter = expression.charAt(i);
            if (Character.toString(caracter).equals("(") ||Character.toString(caracter).equals(")")){
                parentesis++;
                continue;
            }
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
        if ((parentesis%2)==0 && ExpresionCorrecta){
            return false;
        }
        return true;
    }
}
