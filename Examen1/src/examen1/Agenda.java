package examen1;

import java.sql.DriverManager;
import java.sql.Connection;
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

            System.out.println("Introduce tu ip"); //10.230.109.186 (durante el examen)
            ip = esc.next();

            System.out.println("Introduce el nombre de tu base de datos"); //AGENDA
            nombre_base = esc.next();

            // Cargamos la clase que implementa el Driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // Creamos una nueva conexión a la base de datos elegida
            String url = "jdbc:mysql://" + ip + ":3306/" + nombre_base + "?serverTimezone=UTC";
            Connection conexion = DriverManager.getConnection(url, "root", "");
            // Obtenemos un Statement de la conexión
            Statement st = conexion.createStatement();
            // Ejecutamos una consulta SELECT para obtener la tabla vendedores
            System.out.println("Introduce el nombre de la tabla que quieras utilizar"); //contactos
            nombre_tabla = esc.next();
            
            if ("AGENDA".equals(nombre_base)) {
                if ("contactos".equals(nombre_tabla)) {
                    // Pasamos al menu
                    System.out.println("Menú agenda");
                    int menu = 0;

                    while (menu != 5) {
                        System.out.println("1. Mostrar todos los contactos\n2. Añadir contacto\n3. Editar contacto\n4. Eliminar contacto\n5. Salir");
                        menu = esc.nextInt();

                        switch (menu) {
                            case 1:
                                String comando_mysql = "SELECT * FROM " + nombre_tabla;
                                ResultSet consulta = st.executeQuery(comando_mysql);
                                // Recorremos todo el ResultSet y mostramos sus datos
                                while (consulta.next()) {
                                    int id = consulta.getInt("id");
                                    String nombre = consulta.getString("nombre");
                                    int telefono = consulta.getInt("telefono");
                                    System.out.println("Nombre:  " + " | " + nombre + "Telefono: " + telefono + " | " + "ID: " + id);
                                }

                                
                                

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