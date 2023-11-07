public class Main {
    public static void main(String[] args) {
        // Ejercicio #16:

        SimpleREST myServer = new SimpleREST();
        myServer.runServer();

        // Ejercicio #23:
        MoviesREST server = new MoviesREST();
        server.runServer();

    }
}