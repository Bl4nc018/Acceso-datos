import org.json.JSONObject;

public class GrossProfit {

    // Ejercicio #20:

    private int year;
    private long amount; //# Se usa long porque el número puede ser mayor que  2,147,483,647
    private String currencyCode;


    //# Constructor

    public GrossProfit(int year, long amount, String currencyCode) {
        this.year = year;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }


    public JSONObject toJSONObject() {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("year", this.year);
        jsonResult.put("amount", this.amount);
        jsonResult.put("currencyCodeIso4217", this.currencyCode);
        return jsonResult;
    }


}
