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
 /*
 * Clase que lleva la logica del proyecto
 */
public class Logica implements Serializable{

    /**
     * Una lista que guarda la informacion del archivo csv.
     */
    public List<String[]> almacenamiento = Manejador.almacenamiento;
    /**
     * Variable responsable de contener el valor que ingresa el usuario.
     */
    public static String infijo;
    /**
     * Variable que resultante despues de que el conversor evalue a infijo.
     */
    static String postfijo;
    /**
     * Variable que almacena el resultado.
     */
    public static String resultado;

    /**
     * Método que regresa el resultado de la operación realizada por el ususario
     * @return retorna el resultado correspondiente a la operación.
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Método que regresa la expresión matemática infija enviada por el ususario
     * @return retorna la expresión infija del usuario.
     */
    public String getInfijo() {

        return infijo;
    }

    /**
     * Método que regresa una lista con los datos de la operación del usuario.
     * @return retorna una lista que contiene la fecha de realización de la operación, la expresión matemática
     *  del usuario y el resultado de la misma.
     */
    public List<String[]> getAlmacenamiento() {
        return almacenamiento;
    }

    /**
     * Método que recibe la expresión matemática que envía el usuario para la almacenarla o actualizarla.
     * @param infijo , parametro que contiene la expresión matemática enviada por el usuario.
     */
    public void setInfijo(String infijo) {
        Logica.infijo = infijo;
    }

    /**
     * Método que llama a las clases y métodos correspondientes para registra la operación así como
     *  para resolverla.
     * @throws IOException , ataja la excepción en el caso de que no se encontrase el archivo de registro.
     */
    public void resolveroperacion() throws IOException {
        ConvertExpressions Conversor = new ConvertExpressions();
        if(Conversor.verificar(infijo)){
            resultado = "Expresión inválida,verfique de nuevo";
            
        }else{
            Manejador manejoArchivos = new Manejador();
            postfijo = ConvertExpressions.postfijo(infijo); // se envía la expresión infija a convertir a postfija

            // se envía la expresión postfija para construir el árbol de expresión
            ExpressionTree expressionTree = new ExpressionTree();
            NodeTree root = expressionTree.constructorTree(postfijo);

            // se envía la raíz del árbol ya construido, para resolver la operación recorriendo el árbol de expresión
            resultado = String.valueOf(expressionTree.Resultado(root));
            manejoArchivos.enviarInfo();
            System.out.println("Infijo: " +infijo);
            System.out.println("Postfijo: " +postfijo);
            System.out.println("Resultado: " +resultado);
        }

    }
}
