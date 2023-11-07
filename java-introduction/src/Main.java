import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException {
        // #12:
        SimpleMathDemo mates = new SimpleMathDemo();
        System.out.println("Este número es el mayor: " + mates.getTheBiggerNumber(1, 2, 3));
        // #13:
        System.out.println("Este número es el menor: " + mates.getSmallerNumber(1, -3, 2, 10));
        // #14:
        int result = mates.randomNumberInRange(3, 5);
        System.out.println(result);
        // #15:
        Person unaPersona = new Person("Lucas");
        // #19:
        Coin loteria = new Coin(12123);
        System.out.println(loteria.flipCoin());
        // #24:
        TravelStops trivago = new TravelStops();
        trivago.printFirstStop();
        trivago.changeStop(0, "Seúl");
        trivago.printFirstStop();
        // #30:
        HomeCinemaPreferences hP = new HomeCinemaPreferences(true);
        hP.saveExampleXML();
        // #35:
        CardDistributer poKer = new CardDistributer((short) 3, (short) 3);
        poKer.produceJSON();
    }
}
