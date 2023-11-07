import java.sql.*;
import java.util.ArrayList;

public class MoviesDataProvider {

    
    // Ejercicio #1:
    //# Constructor:
    public MoviesDataProvider() {
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        try {
            Connection conn = DriverManager.getConnection(connectionStr);
            System.out.println("Connection established");

            // Ejercicio #3:

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT title FROM TMovies");

            boolean hayMasFilas = result.next();

            while (hayMasFilas) { //# Lector del cursor
                String title = result.getString(1); //# Almacena el título donde se encuentra el cursor.
                System.out.println(title); //# Imprime el título de la película por pantalla.
                hayMasFilas = result.next(); //# Recoje el siguiente resultado, comprobando a la vez que existe.
            }

            // Ejercicio #2:
            conn.close();
            System.out.println("Connection shutdown");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Ejercicio #4:

    public ArrayList<String> getTwoColumns(){
        ArrayList<String> finalResult = new ArrayList<>();
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT genre, title FROM TMovies");
            while(result.next()) {
                String firstColumnValue = result.getString(1);
                String secondColumValue = result.getString(2);
                String concatenated = firstColumnValue + ", " + secondColumValue;
                finalResult.add(concatenated);
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return finalResult;
    }


    // Ejercicio #5:

    public ArrayList<String> getColumnUsingWhere(){
        ArrayList<String> finalResult = new ArrayList<>();
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT title FROM TMovies WHERE year>1990");
            while(result.next()) {
                String firstColumnValue = result.getString(1);
                finalResult.add(firstColumnValue); //# Titulo que obtengamos con el método anterior será añadido al Array list.
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return finalResult;
    }


    // Ejercicio #6:

    public ArrayList<String> getResultsIssue6(){
        ArrayList<String> finalResult = new ArrayList<>();
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT title, year, duration FROM TMovies WHERE countryIso3166 = 'US'");

            while(result.next()) {
                String firstColumnValue = result.getString(1); //# Obtenemos el valor de las columnas.
                String secondColumnValue = result.getString(2);
                String thirdColumnValue = result.getString(3);

                String conc = "-"; //# Concatenamos mucho
                String concatenated = firstColumnValue + conc + secondColumnValue + conc + thirdColumnValue;
                finalResult.add(concatenated);
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return finalResult;
    }


    // Ejercicio #7:

    public String getResultIssue7() {
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        String firstColumnValue = "";
        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT genre FROM TMovies WHERE id = 6");

            while (result.next()) { firstColumnValue = result.getString(1); }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return firstColumnValue;
    }


    // Ejercicio #8:

    public ArrayList<String> getResultsIssue8() {
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        ArrayList<String> finalResult = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT id, title FROM TMovies WHERE year>1986 OR duration<134");

            while (result.next()) {
               String firstColumnValue = result.getString(1); //# Obtengo los valores de las columnas
               String secondColumnValue = result.getString(2);

                String concatenated = firstColumnValue + "-" + secondColumnValue; //# Los meto en un string concadenado y
                finalResult.add(concatenated);  //# son añadidos al resultado final
            }

            conn.close();
        } catch (SQLException e) { throw new RuntimeException(e); }
        return finalResult;
    }


    // Ejercicio #9:

    public ArrayList<String> getResultsIssue9() {
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";
        ArrayList<String> finalResult = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT id, title FROM TMovies WHERE year>1986 AND duration<134");

            while (result.next()) {
                String firstColumnValue = result.getString(1); //# Obtengo los valores de las columnas
                String secondColumnValue = result.getString(2);

                String concatenated = firstColumnValue + "-" + secondColumnValue; //# Los meto en un string concadenado y
                finalResult.add(concatenated);  //# son añadidos al resultado final
            }

            conn.close();
        } catch (SQLException e) { throw new RuntimeException(e); }
        return finalResult;
    }


    // Ejercicio #10:

    public String getResultIssue10() {
        String connectionStr = "jdbc:sqlite:db/sqlite3/movies.db";

        String firstColumnValue = "";
        try {
            Connection conn = DriverManager.getConnection(connectionStr);

            //# Creación + ejecución de la consulta:
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT title FROM TMovies WHERE id = 6");

            while (result.next()) {
                firstColumnValue = result.getString(1); //# Obtengo los valores de las columnas
                
            }

            conn.close();
        } catch (SQLException e) { throw new RuntimeException(e); }
        return firstColumnValue;
    }

}
