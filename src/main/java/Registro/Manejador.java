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
public class Manejador {

    java.util.Date date = new Date();

    public static List<String[]> almacenamiento;


    public void enviarInfo() throws IOException {
        escribir("C:\\Users\\Isac\\OneDrive\\Documentos\\GitHub\\AyEDI-Proyecto-II\\src\\main\\java\\Registro\\registrocsv.txt");
        almacenamiento = convertir("C:\\Users\\Isac\\OneDrive\\Documentos\\GitHub\\AyEDI-Proyecto-II\\src\\main\\java\\Registro\\registrocsv.txt");
    }

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
