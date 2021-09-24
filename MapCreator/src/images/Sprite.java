package images;

public class Sprite {
    private final int HEIGHT;
    private int X;
    private int Y;
    
    private SpriteSheat spriteSheat;
    public int[] pixels;

    //Sprites
    public static Sprite test = new Sprite(0,0,128,SpriteSheat.test);

    public Sprite(final int row,final int column, final int height, final SpriteSheat spriteSheat){
        this.HEIGHT = height;
        this.X = row * HEIGHT;
        this.Y = column * HEIGHT;

        this.pixels = new int[HEIGHT*HEIGHT];

        this.spriteSheat = spriteSheat;

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < HEIGHT; x++) {
                pixels[ y*HEIGHT + x ] = spriteSheat.PIXELS[(Y + y) * spriteSheat.getHeight() + (X + x)];
            }
        }
    }
}
