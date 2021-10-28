package Tree;

import java.util.Stack;

public class ExpressionTree {

    // método para verificar si el argumento dado es un operador.
    public boolean isOperator(String operator) {
        return (operator.equals("+")  ||operator.equals("/")
                || operator.equals("-") || operator.equals("+")
                || operator.equals("*") ||
                operator.equals("%"));
    }

    // método para construir un árbol de expresion de la expresion postfija de entrada
    public NodeTree constructorTree(String postorderExpression) {
        //variable para almacenar los operandos
        String TempNum ="";
        // se crea una pila vacía para almacenar punteros de las raices de los subárboles
        // que componen al árbol de expresión
        Stack<NodeTree> pila = new Stack<>();

        // recorre la expresión postfija para crear el árbol de expresión
        String caracterEvaluado;

        for (int i=0; i<postorderExpression.length();i++) {
            // caracter que se intera de la expresión postfija
            caracterEvaluado = Character.toString(postorderExpression.charAt(i));

            // se inserta los numeros u operandos en la pila como un nodo hoja.
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
            // se almacenan los operandos u números en una variable para luego insertarlos a la pila, esto para el
            // caso de que fuesen números de más de un dígito.
            else {
                TempNum = TempNum + caracterEvaluado;
            }
        }

        // Al crear el árbol de expresión, en la pila permanece un puntero hacia la raiz de ese árbol como
        //  referencia de inicio.
        return pila.peek();
    }

    // método para recorrer calcular la expresión contenida en el árbol
    public double Resultado(NodeTree root) {
        double respuesta = 0; // resultado que se retorna en cada subárbol

        // se cumple al tener como raiz a un operando o nodo hoja
        if (root.left==null && root.right==null){
            return Double.parseDouble(root.data); // se retorna el operando o número contenido en el nodo hoja
        }

        // se realiza recursividad de pila para cálcular cada subárbol del árbol principal
        //  para luego cálcular el hijo izquierdo y derecho de la raiz principal
        switch (root.data){
            // para cada caso se llama recursivamente a los hijos de cada subárbol
            //  de manera que se obtengan los nodos hojas para ser cálculados.
            case "+":
                respuesta = Resultado(root.left) + Resultado(root.right);
                break;
            case "-":
                respuesta = Resultado(root.left) - Resultado(root.right);
                break;
            case "*":
                respuesta = Resultado(root.left) * Resultado(root.right);
                break;
            case "/":
                respuesta = Resultado(root.left) / Resultado(root.right);
                break;
            case "%":
                respuesta = Resultado(root.left) % Resultado(root.right);
                break;
        }
        return respuesta;
    }
}
