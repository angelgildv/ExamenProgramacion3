package Ej1;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;
import java.sql.SQLException;

public class Agenda {

    public static void main(String[] args) {
        try {
            Scanner esc = new Scanner(System.in);
            String nombre_base;
            String ip;
            String nombre_tabla;

            //PRUEBAS DURANTE EXAMEN
            nombre_base = "AGENDA";
            nombre_tabla = "contactos";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://10.230.109.186:3306/AGENDA?serverTimezone=UTC";
            Connection conexion = DriverManager.getConnection(url, "root", "");
            Statement st = conexion.createStatement();

//            System.out.println("Introduce tu ip"); //10.230.109.186 (durante el examen)
//            ip = esc.next();
//            System.out.println("Introduce el nombre de tu base de datos"); //AGENDA
//            nombre_base = esc.next();
//            // Cargamos la clase que implementa el Driver
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            // Creamos una nueva conexión a la base de datos elegida
//            String url = "jdbc:mysql://" + ip + ":3306/" + nombre_base + "?serverTimezone=UTC";
//            Connection conexion = DriverManager.getConnection(url, "root", "");
//            // Obtenemos un Statement de la conexión
//            Statement st = conexion.createStatement();
//            // Pedimos la tabla a utilizar
//            System.out.println("Introduce el nombre de la tabla que quieras utilizar"); //contactos
//            nombre_tabla = esc.next();
            
            if ("AGENDA".equals(nombre_base)) {
                if ("contactos".equals(nombre_tabla)) {
                    // Pasamos al menu
                    System.out.println("Menú agenda");
                    int menu = 0;
                    String comando_mysql = "";
                    ResultSet consulta;
                    String nombre;
                    int telefono;
                    int id;

                    while (menu != 5) {
                        System.out.println("1. Mostrar todos los contactos\n2. Añadir contacto\n3. Editar contacto\n4. Eliminar contacto\n5. Salir");
                        menu = esc.nextInt();
                        switch (menu) {
                            case 1: //mostrar
                                comando_mysql = "SELECT * FROM " + nombre_tabla;
                                consulta = st.executeQuery(comando_mysql);
                                // Recorremos todo el ResultSet y mostramos sus datos
                                while (consulta.next()) {
                                    id = consulta.getInt("id");
                                    nombre = consulta.getString("nombre");
                                    telefono = consulta.getInt("telefono");
                                    System.out.println("Nombre: " + nombre + ", Telefono: " + telefono + " | " + "ID: " + id);
                                }
                                break;
                            case 2: //añadir
                                System.out.println("Elige un nombre, telefono e id");
                                nombre = esc.next();
                                telefono = esc.nextInt();
                                id = esc.nextInt();
                                comando_mysql = "INSERT INTO " + nombre_tabla + " VALUES (?, ?, ?)";
                                // Preparamos los huecos en los que se van a añadir los valores y lo ejecutamos en orden
                                PreparedStatement añadir = conexion.prepareStatement(comando_mysql);
                                añadir.setInt (1, id);
                                añadir.setString (2, nombre);
                                añadir.setInt (3, telefono);
                                añadir.execute();
                                System.out.println("Contacto añadido correctamente.");
                                break;
                            case 3: //editar
                                System.out.println("Escribe el id del contacto que quieres editar (puedes buscarlo en la opcion 1 del menu):");
                                id = esc.nextInt();
                                System.out.println("¿Desea cambiar nombre(1), telefono(2) o ambos(3)?");
                                
                                nombre = esc.next();
                                telefono = esc.nextInt();
                                
                                comando_mysql = "UPDATE " + nombre_tabla + " SET nombre='"+nombre+"' and telefono="+telefono+" WHERE id="+id;
                                st.executeUpdate(comando_mysql);
                                System.out.println("Contacto editado correctamente.");
                                break;
                            case 4: //eliminar
                                System.out.println("Escribe el id del contacto que quieres eliminar (puedes buscarlo en la opcion 1 del menu):");
                                id = esc.nextInt();
                                
                                comando_mysql = "DELETE FROM " + nombre_tabla + " WHERE id="+id;
                                st.executeUpdate(comando_mysql);
                                break;
                        }
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