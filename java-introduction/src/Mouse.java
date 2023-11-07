public class Mouse {
    // Ejercicio #20:
    private boolean isLeftHanded;
    public Mouse(boolean isLeftHanded) {
        this.isLeftHanded = isLeftHanded;
    }
    public void leftButton(){
        if(isLeftHanded){
            System.out.println("Has hecho click secundario");
        }else{
            System.out.println("Has hecho click principal");
        }
    }
    public void clickRightButton(){
        if(isLeftHanded){
            System.out.println("Has hecho click principal");
        }else{
            System.out.println("Has hecho click secundario");
        }
    }
}
