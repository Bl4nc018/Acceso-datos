public class Greeter  {

    // Ejercicio #1:
    public void sayHello(){
        System.out.println("Hello world!");
    }

    // Ejercicio #4:
    public String byeWorld(){
        return "Bye!";
    }

    // Ejercicio #5:
    // Los dos métodos private añadidos, solo devuelven el valor y/o pueden ejecutarse en la clase en la que se hallan creados.
    private int firstNumber() {
        return 15;
    }
    private int secondNumber() {
        return 46;
    }
    public int sumTwoNumbers() {
        return this.firstNumber() + this.secondNumber(); // this se refiere a la instancia actual.
    }
}
