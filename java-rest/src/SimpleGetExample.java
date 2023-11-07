import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class SimpleGetExample extends ServerResource{

    // Ejercicio ejemplo #16:

    @Get
    public String toString() {
        return "Hola, caracola";
    }
}
