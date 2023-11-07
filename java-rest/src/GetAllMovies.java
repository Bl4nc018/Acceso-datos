import org.json.JSONArray;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;

public class GetAllMovies extends ServerResource {

    // Ejercicio #23: + añadido Ejercicio #26:

    @Get
    public StringRepresentation getEndpointResponse() {
        MoviesConnector connector = new MoviesConnector();
        JSONArray resultArray = new JSONArray();

        String orderByYearDesc = getQueryValue("orderByYearDesc");
        boolean shouldShowYear = false;
        if (orderByYearDesc != null){
            shouldShowYear = Boolean.parseBoolean(orderByYearDesc);
        }

        ArrayList<Movie> databaseFilms = connector.getAll(shouldShowYear);

        for (Movie mov : databaseFilms) {
            resultArray.put(mov.toJSONObject());
        }

        connector.closeConnection();

        String jsonString = resultArray.toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }
}
