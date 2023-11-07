import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AnotherGetExample extends ServerResource {

    // Ejercicio #17:

    @Get
    public String toString() {
        return "Mi segundo endpoint";
    }
}
