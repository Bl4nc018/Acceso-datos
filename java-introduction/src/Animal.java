public class Animal {
    // Ejercicio #18:
    private String name;
    private String species;
    private int numberOfTeeth;

    public Animal(String name, String species, int numberOfTeeth) {
        this.name = name;
        this.species = species;
        this.numberOfTeeth = numberOfTeeth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getNumberOfTeeth() { return numberOfTeeth;  }

    public void setNumberOfTeeth(int numberOfTeeth) {
        this.numberOfTeeth = numberOfTeeth;
    }
}
