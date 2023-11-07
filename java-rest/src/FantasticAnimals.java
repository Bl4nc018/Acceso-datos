import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FantasticAnimals extends ServerResource {


    // Ejercicio #22:

    @Get
    public StringRepresentation getEndpointResponse() {
        Magic magic = new Magic(
                42,
                "Puede pulverizar a sus enemigos con una llamarada de fuego"
        );
        Animal animal = new Animal(
                "Duende rojo del volcán",
                342,
                true,
                magic);

        String jsonString = animal.toJSONObject().toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }

}

