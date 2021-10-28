import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;


@ManagedBean
@RequestScoped
public class Calculadora implements Serializable {

    static String infix;

    static String postfix;

    static String resultado;


    public String getResultado() {
        return resultado;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        Calculadora.infix = infix;
    }

    public void resolveroperacion() {
        postfix = ConvertExpressions.postfijo(infix);
        ExpressionTree et = new ExpressionTree();
        NodeTree root = et.constructorTree(postfix);
        et.Resultado(root);
        resultado = String.valueOf(et.Resultado(root));
    }
}
