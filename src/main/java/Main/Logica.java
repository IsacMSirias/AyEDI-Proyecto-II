package Main;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import Tree.ExpressionTree;
import Tree.NodeTree;
import Registro.Manejador;

@ManagedBean
@RequestScoped
public class Logica implements Serializable {


    public List<String[]> almacenamiento = Manejador.almacenamiento;

    public static String infijo;

    static String postfijo;

    public static String resultado;


    public String getResultado() {
        return resultado;
    }

    public String getInfijo() {

        return infijo;
    }

    public List<String[]> getAlmacenamiento() {
        return almacenamiento;
    }

    public void setInfijo(String infijo) {
        Logica.infijo = infijo;
    }

    public void resolveroperacion() throws IOException {
        ConvertExpressions Conversor = new ConvertExpressions();
        if(Conversor.verificar(infijo)){
            resultado = "Expresión inválida,verfique de nuevo";
            
        }else{
            Manejador manejo = new Manejador();
            postfijo = ConvertExpressions.postfijo(infijo);
            ExpressionTree et = new ExpressionTree();
            NodeTree root = et.constructorTree(postfijo);
            et.Resultado(root);
            resultado = String.valueOf(et.Resultado(root));
            manejo.enviarInfo();
        }

    }
}
