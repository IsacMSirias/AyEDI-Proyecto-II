package Tree;

/**
 * Clase que construye los nodos del árbol de expresión binaria.
 * @author Ludwin Ramos, Isac Marín
 * @version 1.0
 * @since 08/11/2021
 */
public class NodeTree {

    String data;
    NodeTree left,right;

    /**
     * Constructor de nodos hojas u nodos de operandos.
     * @param data , parametro que contiene un operando o valor numérico de la expresión matemática del usuario.
     */
    public NodeTree(String data) {
        this.data = data;
        this.left= null;
        this.right= null;
    }

    /**
     * Constructor de nodos internos u nodos de operadores.
     * @param data , parámetro que contiene un operador de la expresión matemática del usuario.
     * @param left , parámetro que contiene los subárboles izquierdos de la raíz del árbol o las raíces internas
     *              del árbol de expresión que representa un conjunto de operaciones.
     * @param right , parámetro que contiene los subárboles derechos de la raíz del árbol o las raíces internas
     *              del árbol de expresión que representa un conjunto de operaciones.
     */
    public NodeTree(String data, NodeTree left, NodeTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}