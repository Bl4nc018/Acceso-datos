public class Coin {
    // Ejercicio #19:
    private int reward;

    public Coin(int reward) {
        this.reward = reward;
        if(reward<=0){
            throw new RuntimeException("Recompensa no válida");
        }
    }
    public String flipCoin(){
        int flip = (int) (Math.round(Math.random()));
        String popUp;
        if(flip==0){
            popUp="Has tirado al aire la moneda y ha salido cruz. Perdiste";
        }else {
            popUp="Has tirado al aire la moneda y ha salido cara. Ganaste " + this.reward + " euros";
        }
        return popUp;
    }

}
