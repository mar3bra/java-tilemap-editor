package controls;

import java.awt.MouseInfo;

public class Mouse {
    
    public static double mouseX;
    public static double mouseY;

    public static void getCoords(){
        mouseX = MouseInfo.getPointerInfo().getLocation().getX();
        mouseY = MouseInfo.getPointerInfo().getLocation().getY();

        System.out.println(mouseX);
        System.out.println(mouseY);

    }
    
}
