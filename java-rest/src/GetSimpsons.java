import org.json.JSONArray;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;



public class GetSimpsons extends ServerResource{

    @Get
    public StringRepresentation getEndpointResponse() {

        Simpsons connector = new Simpsons();
        JSONArray resultArray = new JSONArray();

        String milliseconds = getQueryValue("milliseconds");
        String clipId = getAttribute("clipID");

        ArrayList<Character> databaseFilms = connector.getSimpsons(Integer.parseInt(clipId),Integer.parseInt(milliseconds));

        for (Character character : databaseFilms) {
            resultArray.put(character.toJSONObject());
        }

        connector.closeConnection();

        String jsonString = resultArray.toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }

}
