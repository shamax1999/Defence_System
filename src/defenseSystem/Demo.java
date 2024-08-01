package defenseSystem;

public class Demo {
    public static void main(String[] args) {
        MainController obj = new MainController();
        obj.addComponent(new Tank(obj));
        obj.addComponent(new Submarine(obj));
        obj.addComponent(new Helicopter(obj));
    }
}
