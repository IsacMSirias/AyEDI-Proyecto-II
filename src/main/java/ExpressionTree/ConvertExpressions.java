package ExpressionTree;

import java.util.Stack;

public class ConvertExpressions {
    private String expresion;
    Stack<String> pila = new <Character>Stack();

    private String postfijo(String cadena){
        if (verificar(cadena)){
            return "La expresion no está correctamente escrita; verifique de nuevo";
        }
        else{
            return postfijoAux(cadena);
        }
    }

    public String postfijoAux(String cadena){
        pila.empty();
        String postorderExpression = "";
        char caracterEvaluado;
        boolean temp=true;
        for (int i=0;i<cadena.length();i++){
            caracterEvaluado = cadena.charAt(i);
            if (Character.isDigit(caracterEvaluado)) {
                postorderExpression = postorderExpression + Character.toString(caracterEvaluado);
                temp=true;
                continue;
            }
            switch(caracterEvaluado) {
                case ')':
                    postorderExpression = postorderExpression + pila.pop();
                    temp=false;
                    break;
                default:
                    if (!Character.toString(caracterEvaluado).equals("(") & !Character.isDigit(caracterEvaluado)) {
                        pila.push(Character.toString(caracterEvaluado));
                        if (temp){
                            postorderExpression = postorderExpression +" ";
                        }
                        break;
                    }
            }
        }
        postorderExpression=postorderExpression + pila.pop();
        return postorderExpression;
    }

    public boolean verificar(String expression){
        char caracter;
        int parentesis = 0 ;
        for (int i=0; i<expression.length();i++){
            caracter = expression.charAt(i);
            if (Character.toString(caracter).equals("(") ||Character.toString(caracter).equals(")")){
                parentesis++;
            }
        }
        if ((parentesis%2)==0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ConvertExpressions p=new ConvertExpressions();
        System.out.println(p.postfijo("(12+5)*(1*2)"));
    }
}
