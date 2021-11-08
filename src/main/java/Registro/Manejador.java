package Registro;

import Main.Logica;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ManagedBean
@RequestScoped
/**
 * Clase que se encarga del registro de la operación enviada del usuario.
 *  Registra la fecha, la expresión metemática enviada por el usuario y el resultado de la misma.
 * @author Ludwin Ramos, Isac Marín
 * @version 1.0
 * @since 08/11/2021
 */
public class Manejador {

    java.util.Date date = new Date();

    public static List<String[]> almacenamiento;

    /**
     * Clase encargada de llamar a los métodos para escribir la expresión y almacenar la expresión.
     * @throws IOException, ataja la excepción en el caso de que no se encontrase el archivo de registro.
     */
    public void enviarInfo() throws IOException {
        escribir("C:\\Users\\Isac\\OneDrive\\Documentos\\GitHub\\AyEDI-Proyecto-II\\src\\main\\java\\Registro\\registrocsv.txt");
        almacenamiento = convertir("C:\\Users\\Isac\\OneDrive\\Documentos\\GitHub\\AyEDI-Proyecto-II\\src\\main\\java\\Registro\\registrocsv.txt");
    }

    /**
     * Clase que construye una lista con los datos de la operación.
     * @param ruta , parametro que contiene la ruta del archivo que contiene los datos de las operaciones que envía
     *             el usuario.
     * @return retorna una lista con los datos de la operación que se va recibiendo del usuario.
     * @throws IOException, ataja la excepción en el caso de que no se encontrase el archivo de registro.
     */
    public List<String[]> convertir (String ruta) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            List<String[]> data= reader.readAll();
            data.forEach(x -> System.out.println(Arrays.toString(x)));
            data.remove(0);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Escribe los datos de cada operación que se recibe en un archivo de texto.
     * @param filePath , parametro que contiene la ruta del archivo de registro
     */
    public void escribir(String filePath) {
        try {
            CSVWriter lectordearchivo = new CSVWriter(new FileWriter(filePath, true));
            String[] data = {Logica.infijo, Logica.resultado, date.toString()};
            lectordearchivo.writeNext(data);
            lectordearchivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
