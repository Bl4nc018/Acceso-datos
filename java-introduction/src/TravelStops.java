public class TravelStops {
    // Ejercicio #21:
    private String[] stops;

    public TravelStops() {
        stops = new String[]{"Tokyo", "Frankfurt", "Ciudad del Cabo"};
    }
    public void printFirstStop() {
        System.out.println(this.stops[0]);
    }

    public void printSecondStop() {
        System.out.println(this.stops[1]);
    }

    public void printThirdStop() {
        System.out.println(this.stops[2]);
    }

    // Ejercicio #22:
    public void printStop(int position) {
        System.out.println(this.stops[position]);
    }

    // Ejercicio #23:
    public void printAllStops(){
        for (int i = 0; i < 3; i++) {
            System.out.println(this.stops[i]);
        }
    }
    // Ejercicio #24:
    public void changeStop(int change, String stop){
        for (int i = change; i < 3; i++) {
            this.stops[i]=stop;
        }
    }
}
