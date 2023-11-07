import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class JSONGetCompany2 extends ServerResource {


    // Ejercicio #21:

    @Get
    public StringRepresentation getEndpointResponse() {
        GrossProfit profit = new GrossProfit(
                2015,
                5024938L, // En Java, los long terminan en "L"
                "PLN"
        );
        Company company = new Company(
                "Netflux",
                "John Doe",
                profit
        );

        String jsonString = company.toJSONObject().toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }

}
