public class Person {
    // Ejercicio #16:
    private String name;

    // Ejercicio #15:
    public Person(String name){
        System.out.println(name + " acaba de ser instanciado");
        this.name=name;
    }

    // Ejercicio #17:
    public String getName() {
        return this.name;
    }
    public String greeting() {
        return "Hola, " + this.name;
    }

}
