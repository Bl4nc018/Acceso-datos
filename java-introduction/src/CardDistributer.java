import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardDistributer {
    // Ejercicio #35:
    private short nPlayers;
    private short nCardsP;
    private ArrayList<String> cardProhibiter = new ArrayList<>();

    //# Builder recibirá los datos para poder realizar los setters a no ser que estos sean erróneos y,
    //# por tanto, lance una excepción para adevertir al usuario.
    public CardDistributer(short nPlayers, short nCardsP) {
        if(nPlayers<=0 || nPlayers>4){
            throw new RuntimeException();
        }else{
            setPlayers(nPlayers);
        }
        if(nCardsP<=0 || nCardsP>8){
            throw new RuntimeException();
        }else{
            setCardsP(nCardsP);
        }
    }

    //# Getters y setters para poder conseguir datos adecuados del constructor:
    public short getPlayers() { return nPlayers; }
    public void setPlayers(short nPlayers) { this.nPlayers = nPlayers; }
    public short getCardsP() { return nCardsP; }
    public void setCardsP(short nCardsP) { this.nCardsP = nCardsP;}

    public JSONArray cardsMaker(){
        ArrayList<String> tCard = new ArrayList<>(List.of("O", "C", "E", "B"));
        JSONArray cards = new JSONArray();
        int selector;
        int selector2;
        String cartita = "";

        for(int i=0; i<getCardsP(); i++) {
            do{
                selector=(int) (Math.random() * (10)+1);
                selector2=(int) (Math.random() * 4);
                if (selector == 8) {
                    cartita=tCard.get(selector2)+"S";
                } else if (selector == 9) {
                    cartita=tCard.get(selector2)+"C";
                } else if (selector == 10) {
                    cartita=tCard.get(selector2)+"R";
                } else {
                    cartita=tCard.get(selector2)+selector;
                }
            }while(cardProhibiter.contains(cartita));
            cardProhibiter.add(cartita);
            cards.put(cartita);
        }
        return cards;
    }

    public void produceJSON(){
        JSONObject poker = new JSONObject();
        for(int i=0; i<getPlayers(); i++){
            poker.put(("player"+(i+1)), cardsMaker());
        }
        try {
            FileWriter writer0 = new FileWriter("assets\\cards.json");
            poker.write(writer0, 2, 0);
            writer0.flush();
            writer0.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cardProhibiter.clear();
    }



}
