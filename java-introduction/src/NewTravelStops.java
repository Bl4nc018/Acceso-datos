import java.util.ArrayList;
import java.util.List;
public class NewTravelStops {
    // Ejercicio #25:
    private ArrayList<String> newStops;
    public NewTravelStops() {
        newStops = new ArrayList<>(List.of("Kyoto", "Singapur", "Hanoi"));
    }
    public void printStop(int index){
        System.out.println(newStops.get(index));
    }
    public void changeStop(int index, String newValue){
        newStops.set(index, newValue);
    }
    public void addStop(String newValue){
        newStops.add(newValue);
    }

    // Ejercicio #26:
    public void printAllStops(){
        for(String stop : newStops){ System.out.println(stop); }
    }

}
