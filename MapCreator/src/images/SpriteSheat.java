package images;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteSheat {
    public final int[] PIXELS;
    private final int WIDTH;
    private final int HEIGHT;

    

    //importar los Sprite Sheat
    public static SpriteSheat test = new SpriteSheat("/resources/first.png", 256, 256);
    
    public SpriteSheat(final String path, final int width, final int height){
        this.PIXELS = new int[width * height];
        
        this.WIDTH = width;
        this.HEIGHT = height;
        
        try {
            BufferedImage spriteSheat = ImageIO.read(getClass().getResource(path));
            spriteSheat.getRGB( 0, 0, WIDTH, HEIGHT, PIXELS, 0, WIDTH);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getHeight(){
        return HEIGHT;
    }
}
