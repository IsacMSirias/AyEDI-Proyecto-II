package ExpressionTree;

import java.util.Stack;

public class ExpressionTree {

    // método para verificar si el argumento dado es un operador.
    public boolean isOperator(char operator) {
        return (operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator=='%');
    }

    // método para construir un árbol de expresion de la expresion postfija de entrada
    public NodeTree construct(String postorderExpression)
    {
        // se crea una pila vacía para almacenar punteros de las raices de los subárboles
        // que componen al árbol de expresión
        Stack<NodeTree> pila = new Stack<>();

        // recorre la expresión postfija para crear el árbol de expresión
        char caracterEvaluado;
        for (int i=0; i<postorderExpression.length();i++) {
            // caracter que se intera de la expresión postfija
            caracterEvaluado = postorderExpression.charAt(i);

            // se cumple si el caracter evaluado es un operador
            if (isOperator(caracterEvaluado))
            {
                // retira los dos últimos nodos o elementos de 'x' y 'y' de la pila
                NodeTree right = pila.pop();
                NodeTree left = pila.pop();

                // se construye un nuevo árbol de expresion, cuya raiz es el caracter evaluado y
                // siendo las variables left y right los hijos de esa raiz.
                NodeTree node = new NodeTree(caracterEvaluado, left, right);

                // se inserta el nuevo subárbol dentro de la pila
                pila.add(node);
            }

            // se cumple cuando el caracter evaluado es un operando, es decir un dígito.
            // se inserta en la pila como un nodo hoja.
            else {
                pila.add(new NodeTree(caracterEvaluado));
            }
        }

        // Al crear el árbol de expresión, en la pila permanece un puntero hacia la raiz de ese árbol como
        //  referencia de inicio.
        return pila.peek();
    }
}
