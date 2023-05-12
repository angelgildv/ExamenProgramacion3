/*
El ayuntamiento de madrid nos ofrece los datos de los aparcamientos del municipio en el archivo adjunto "aparcamientos.csv".
Solo podemos leer una vez los datos del archivo y almacenarlos en la jerarquia de clases necesaria,
que permita en un futuro realizar calculos con los datos de aparcamientos.
Por ahora, solo queremos calcular el numero de aparcamientos por cada tipo y el total de aparcamientos por distrito.
*/
import java.io.File;

public class Ej2 {
    public static void main(String[] args) {
        File f = new File("Aparcamientos.csv");
    }
}