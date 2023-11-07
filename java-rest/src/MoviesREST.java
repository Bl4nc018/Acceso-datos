import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
public class MoviesREST {


    // Ejercicio #23:

    private Component component;
    public void runServer() {
        try {
            this.component = new Component();
            this.component.getServers().add(Protocol.HTTP, 8104);
            VirtualHost host = this.component.getDefaultHost();
            this.component.start();
            host.attach("/movies", GetAllMovies.class);

            // Ejercicio #24:
            host.attach("/movies/{movieID}", GetMovieByID.class);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void stopServer() throws Exception {
        if (this.component != null) {
            this.component.stop();
        }
    }
}

