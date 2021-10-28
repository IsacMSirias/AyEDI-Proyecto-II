public class NodeTree {

    String data;
    NodeTree left,right;

    // constructor de nodos hojas u nodos de operandos.
    public NodeTree(String data) {
        this.data = data;
        this.left= null;
        this.right= null;
    }

    // constructor de nodos internos u nodos de operadores.
    public NodeTree(String data, NodeTree left, NodeTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}