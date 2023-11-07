
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class GetMovieByID extends ServerResource {


    // Ejercicio #24:

    @Get
    public StringRepresentation getEndpointResponse() {
        String movieID = getAttribute("movieID");
        Integer movieInt = Integer.parseInt(movieID);
        MoviesConnector connector = new MoviesConnector();

        // Ejercicio #25:

        Movie movie = connector.getMovieUsingID(movieInt);
        if (movie == null) {
            throw new ResourceException(404, "The movie was not found");
        }
        connector.closeConnection();

        String jsonString = movie.toJSONObject().toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }
}
