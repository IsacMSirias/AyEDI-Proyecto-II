package ExpressionTree;

public class Main {
    ExpressionTree arbolExpresion = new ExpressionTree();
    ConvertExpressions conversor;

    public Main() {
        conversor = new ConvertExpressions();
    }

    public double calcular(String cadena) {
        // Verfica si la expresión está escrita correctamente, si no rectorna un cero
        if (conversor.verificar(cadena)) {
            return 0;

        // se calcula la expresión del usuario
        } else {
            String posfija = conversor.postfijo(cadena);
            NodeTree Arbol = arbolExpresion.constructorTree(posfija);
            return arbolExpresion.Resultado(Arbol);
        }
    }
}

