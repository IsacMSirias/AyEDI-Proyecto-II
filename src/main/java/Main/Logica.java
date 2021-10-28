package Main;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import Tree.ExpressionTree;
import Tree.NodeTree;

@ManagedBean
@RequestScoped
public class Logica implements Serializable {

    static String infijo;

    static String postfijo;

    static String resultado;


    public String getResultado() {
        return resultado;
    }

    public String getInfijo() {
        return infijo;
    }

    public void setInfijo(String infijo) {
        Logica.infijo = infijo;
    }

    public void resolveroperacion() {
        postfijo = ConvertExpressions.postfijo(infijo);
        ExpressionTree et = new ExpressionTree();
        NodeTree root = et.constructorTree(postfijo);
        et.Resultado(root);
        resultado = String.valueOf(et.Resultado(root));
    }
}
