import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;

public class SimpleREST {

    // Ejercicio #16:

    private Component component;

    public void runServer() {
        this.component = new Component();
        this.component.getServers().add(Protocol.HTTP, 8104);
        VirtualHost host = this.component.getDefaultHost();
        host.attach("/example", SimpleGetExample.class);

        // Ejercicio #17:
        host.attach("/example2", AnotherGetExample.class);

        // Ejercicio #18:
        host.attach("/example3", JSONGetExample.class);

        // Ejercicio #19:
        host.attach("/example4", JSONGetAnotherExample.class);

        // Ejercicio #20:
        host.attach("/company", JSONGetCompany.class);

        // Ejercicio #21:
        host.attach("/otherCompany", JSONGetCompany2.class);

        // Ejercicio #22:
        host.attach("/creature", FantasticAnimals.class);


        try {
            this.component.start();
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
