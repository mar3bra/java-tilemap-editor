package graphics;

import images.Sprite;
import java.awt.Color;

public final class Screen{
    //Dimensiones de la pantalla
    private final int height;
    private final int width;

    public static final int OFFSET_X = 250;
    public static final int OFFSET_Y = 30;

    //Array de color de pixeles
    public int[] pixels;

    //Temp
    private final int lado = 32;
    private final int mask = lado - 1;

    public Screen(int width, int height){
        
        this.height = height;
        this.width = width;

        
        this.pixels = new int[height*width];
    }


    public void clearScreen(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y * width + x] = new Color(170,170,170).getRGB();
            }
        }
    }

    public void drawScreen() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                if (y % lado == 0 || x % lado == 0){
                    pixels[y*width  + x] = new Color(130,130,130).getRGB();
                }                
            }
        }
    }
}