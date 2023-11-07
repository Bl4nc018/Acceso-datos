import java.util.Random;

public class SimpleMathDemo {
    // Ejercicio #6:
    private int firstNumber() {return 2627;}
    private int secondNumber() {return 3163;}
    private int thirdNumber() {return 4231;}

    public int getResult(){
        return this.firstNumber()+this.secondNumber()+this.thirdNumber();
    }

    // Ejercicio #7:
    public void printMultiplication() {
        System.out.println("Los múltiplos de 7 menores que 70 son:");
        for (int i = 0; i < 70; i+=7) { System.out.println(i); }
    }
    public void loop50Times() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Imprimiendo... " + i);
            if (i == 38) {
                return; // Se puede hacer return si el método devuelve void. Tiene el mismo efecto... ¡Pero no devuelve nada!
            }
        }
    }

    // Ejercicio #8:
    public void printNumber(int numerito) {
        System.out.println(numerito);
    }

    // Ejercicio #9:
    public void printProduct(int numerito1, int numerito2) {
        System.out.println(numerito1*numerito2);
    }

    // Ejercicio #10:
    // #Escribir 2 métodos con el mismo nombre en la misma clase, está permitido, y se conoce como sobrecarga.
    // #Es decir, que el método printProduct está sobrecargado con distintos parámetros.
    public void printProduct(String var, int num1, int num2) {
        System.out.println("Hola," + " " + var);
        System.out.println("Este es el producto de tus dos valores:");
        System.out.println(num1 * num2);
    }

    // Ejercicio #12:
    public int getTheBiggerNumber(int big1, int big2, int big3){
        int biggerF = 0;
        if(big1>=big2 && big1>=big3) {
            biggerF=big1;
        } else if (big2>=big1 && big2>=big3) {
            biggerF=big2;
        } else if(big3>=big1 && big3>=big2){
            biggerF=big3;
        }
        return biggerF;
    }

    // Ejercicio #13:
    public int getSmallerNumber(int small1, int small2, int small3, int small4){
        int smallerF = 0;
        if(small1<=small2 && small1<=small3 && small1<=small4) {
            smallerF=small1;
        } else if (small2<=small1 && small2<=small3 && small2<=small4) {
            smallerF=small2;
        } else if(small3<=small1 && small3<=small2 && small3<=small4){
            smallerF=small3;
        } else if(small4<=small2 && small4<=small3 && small4<=small1){
            smallerF=small4;
        }
        return smallerF;
    }

    // Ejercicio #14:
    public int randomNumberInRange(int start, int length) {
        int RandomNum = 0;
        if (length < 0) {
            RandomNum = -1;
        } else {
            RandomNum = (int) (Math.random() * (length)) + start;
        }
        return RandomNum;
    }
}
