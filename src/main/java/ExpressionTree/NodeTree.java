package ExpressionTree;

public class NodeTree {

    char data;
    NodeTree left,right;

    // constructor de nodos hojas u nodos de operandos.
    public NodeTree(char data) {
        this.data = data;
        this.left= null;
        this.right= null;
    }

    // constructor de nodos internos u nodos de operadores.
    public NodeTree(char data, NodeTree left,NodeTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
