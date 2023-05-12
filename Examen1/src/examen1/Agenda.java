package examen1;

/*
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;
import java.sql.SQLException;

public class Agenda {

    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        System.out.println("Menú agenda");
        int menu = 0;
        
        while (menu != 5) {
            System.out.println("1. Mostrar todos los contactos\n2. Añadir contacto\n3. Editar contacto\n4. Eliminar contacto\n5. Salir");
            menu = esc.nextInt();
            
            switch (menu) {
                case 1:
                case 2:
                    System.out.println("Introduce el nombre del contacto");
                    System.out.println("Comprobación: "+agenda.eliminarContacto(esc.next()));
                    break;
                case 3:
                    System.out.println("Introduce el nombre del contacto");
                    System.out.println("¿Existe?: "+agenda.existeContacto(esc.next()));
                    break;
                case 4:
                    agenda.listarContactos();
                    break;
                    int opcion = 0;
                    while (opcion != 3) {
                        System.out.println("Tipo de contacto: 1-Persona, 2-Empresa");
                        opcion = esc.nextInt();
                        switch (opcion) {
                            case 1:
                                System.out.println("Introduce los datos del contacto");
                                ContactoPersona cc = new ContactoPersona(esc.next(), esc.nextInt(), esc.nextLine());
                                System.out.println("Confirmación del proceso: "+agenda.añadirContacto(cc));
                                break;
                            case 2:
                                System.out.println("Introduce los datos del contacto");
                                ContactoEmpresa ce = new ContactoEmpresa(esc.next(), esc.nextInt(), esc.nextLine());
                                System.out.println("Confirmación del proceso: "+agenda.añadirContacto(ce));
                                break;
                        }
                    }
                    break;
                case 5:
                    break;
            }
            
            
            
            
            
            
            
            
        try {
            Scanner lector = new Scanner(System.in);
            String nombre_base;
            String ip;
            String nombre_tabla;

            System.out.println("Introduce tu ip"); //10.230.109.186 (durante el examen)
            ip = lector.next();

            System.out.println("Introduce el nombre de tu base de datos"); //AGENDA
            nombre_base = lector.next();

            // Cargamos la clase que implementa el Driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // Creamos una nueva conexión a la base de datos elegida
            String url = "jdbc:mysql://" + ip + ":3306/" + nombre_base + "?serverTimezone=UTC";
            Connection conexion = DriverManager.getConnection(url, "root", "");
            // Obtenemos un Statement de la conexión
            Statement st = conexion.createStatement();
            // Ejecutamos una consulta SELECT para obtener la tabla vendedores
            System.out.println("Introduce el nombre de la tabla que quieras visualizar"); //contactos
            nombre_tabla = lector.next();
            String comando_mysql = "SELECT * FROM " + nombre_tabla;
            ResultSet consulta = st.executeQuery(comando_mysql);

            // Recorremos todo el ResultSet y mostramos sus datos
            if ("AGENDA".equals(nombre_base)) {

                if ("contactos".equals(nombre_tabla)) {
                    while (consulta.next()) {
                        int id = consulta.getInt("id");
                        String nombre = consulta.getString("nombre");
                        int telefono = consulta.getInt("telefono");
                        System.out.println("ID: " + id + " | " + "Nombre:  " + " | " + nombre + "Telefono: " + telefono);
                    }
                }
            } else {
                System.out.println("Base de datos no encontrada");
            }
            // Cerramos el statement y la conexión
            st.close();
            conexion.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Excepcion: " + e);
        }
    }
}