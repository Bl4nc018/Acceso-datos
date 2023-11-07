import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class JSONGetCompany extends ServerResource {

    // Ejercicio #20:

    @Get
    public StringRepresentation getEndpointResponse() {
        GrossProfit profit = new GrossProfit(
                2021,
                54577000000L, // En Java, los long terminan en "L"
                "USD"
        );
        Company company = new Company(
                "Amazon",
                "Jeff Bezos",
                profit);

        String jsonString = company.toJSONObject().toString();
        StringRepresentation representation = new StringRepresentation(jsonString);
        representation.setMediaType(MediaType.APPLICATION_JSON);
        return representation;
    }
}

