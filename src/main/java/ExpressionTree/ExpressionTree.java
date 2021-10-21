package ExpressionTree;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionTree {

    // método para verificar si el argumento dado es un operador.
    public boolean isOperator(String operator) {
        return (operator.equals("+")  || operator.equals("-") || operator.equals("+") || operator.equals("*") ||
                operator.equals("%"));
    }

    // método para construir un árbol de expresion de la expresion postfija de entrada
    private NodeTree constructorTree(String postorderExpression) {

        String TempNum ="";
        // se crea una pila vacía para almacenar punteros de las raices de los subárboles
        // que componen al árbol de expresión
        Stack<NodeTree> pila = new Stack<>();

        // recorre la expresión postfija para crear el árbol de expresión
        String caracterEvaluado;

        for (int i=0; i<postorderExpression.length();i++) {
            // caracter que se intera de la expresión postfija
            caracterEvaluado = Character.toString(postorderExpression.charAt(i));

            if (caracterEvaluado.equals(" ")) {

                pila.add(new NodeTree(TempNum));
                TempNum="";
                continue;
            }
            // se cumple si el caracter evaluado es un operador
            if (isOperator(caracterEvaluado)) {

                // retira los dos últimos nodos o elementos de 'x' y 'y' de la pila
                NodeTree right = pila.pop();
                NodeTree left = pila.pop();

                // se construye un nuevo árbol de expresion, cuya raiz es el caracter evaluado y siendo las
                //  variables left y right los hijos de esa raiz.
                NodeTree node = new NodeTree(caracterEvaluado, left, right);

                // se inserta el nuevo subárbol dentro de la pila
                pila.add(node);

            }
            else if (caracterEvaluado.equals(" ")) {

                pila.add(new NodeTree(TempNum));
                TempNum="";
                continue;
            }
            // se cumple cuando el caracter evaluado es un operando, es decir un dígito.
            // se inserta en la pila como un nodo hoja.
            else {
                TempNum = TempNum + caracterEvaluado;
            }
        }

        // Al crear el árbol de expresión, en la pila permanece un puntero hacia la raiz de ese árbol como
        //  referencia de inicio.
        return pila.peek();
    }

    // método para imprimir la expresión del árbol
    public void inorder(NodeTree root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);
    }
}
