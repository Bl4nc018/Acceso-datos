import java.sql.*;
import java.util.ArrayList;

public class Simpsons {

    private Connection connection;

    public Simpsons() {
        String connectionStr = "jdbc:sqlite:db/sqlite3/simpsons.db";
        try {
            this.connection = DriverManager.getConnection(connectionStr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Character> getSimpsons(int clipId, int milliseconds) {
        ArrayList<Character> allCharacters = new ArrayList<>();

        String sql="SELECT name, surname, description, imageUrl FROM TCharacters "+
                "JOIN TAppearances ON TCharacters.id=TAppearances.characterId "+
                "JOIN TClips ON TAppearances.clipId=TClips.id WHERE TClips.id ="+clipId;
        sql+=" AND TAppearances.startMilliseconds <= "+milliseconds+" AND TAppearances.endMilliseconds >= "+milliseconds;


        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                String firstName = result.getString(1);
                String surname = result.getString(2);
                String desc = result.getString(3);
                String imageUrl = result.getString(4);
                Character simps = new Character(firstName, surname, desc, imageUrl);
                allCharacters.add(simps);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCharacters;
    }



    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}