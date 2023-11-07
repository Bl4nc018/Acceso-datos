import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class JSONGetAnotherExample extends ServerResource {

    // Ejercicio #19:

    @Get
    public StringRepresentation getEndpointResponse() {
        JSONObject json = new JSONObject();
        json.put("characterPopularity", 10);
        json.put("characterDescription", "Lisa Simpson es la pequeña saxofonista que adora el Jazz y a los animales");
        String jsonString = json.toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }
}
