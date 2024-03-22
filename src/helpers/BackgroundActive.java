package helpers;

public class BackgroundActive {
    private static boolean active = false;

    public static boolean switchOn() {
        if (active) {
            return false;
        }
        active = true;
        return true;
    }

    public static boolean switchOff(){
        if(!active){return false;}
        active=false;
        return true;
    }
    public static boolean isActive() {
        return active;
    }
}
