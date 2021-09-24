import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controls.Keyboard;
import graphics.Panels;
import graphics.Screen;
import maps.Map;

public class App extends Canvas implements Runnable{
    
    //Variables de la Ventana
    private JFrame window;

    private static final String NAME = "Map Creator";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    
    //Panels 
    private Panels panels;
    
    //Thread
    private static Thread thread;

    //Bucle principal
    public static Boolean engineOn;
    private static int fps = 0;
    private static int ups = 0;

    //Keyboard
    public static Keyboard keyboard;

    //Pantalla
    private static int screenWidth = (WIDTH - Screen.OFFSET_X)/3;
    private static int screenHeight = (HEIGHT - Screen.OFFSET_Y)/3;

    private static Screen screen;
    private static BufferedImage bufferedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
    private static int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
    


    private App(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        //map Window
        window = new JFrame(NAME);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        panels = new Panels(WIDTH, HEIGHT);
        window.add(panels.leftPanel);
        window.add(panels.topPanel);



        window.add(this, BorderLayout.CENTER);


        window.pack(); //Ajusta la pantalla a setPreferredSize();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //set Panels


        keyboard = new Keyboard();
        addKeyListener(keyboard);

        screen = new Screen(screenWidth, screenHeight);

    }

    public static void main(String[] args) throws Exception {

        App app = new App();
        app.startThread();
     
    }

    private synchronized void startThread(){
        engineOn = true;
        thread = new Thread(this, "Graphics");
        thread.start();
    } 

    private synchronized void stopThread(){
        engineOn = false;
        try {
            thread.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void update(){
        ups++;
        keyboard.update();
        
        if (keyboard.a) {
            System.out.println("Left");
        }
        System.out.println(panels.layerSlider.getValue());
        //Mouse.getCoords();
    }

    private void display(){
        fps++;
        BufferStrategy strat = getBufferStrategy();

        if (strat == null){
            createBufferStrategy(2);
            return;
        }

        screen.clearScreen();
        screen.drawScreen();

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Graphics g = strat.getDrawGraphics();
        g.drawImage(bufferedImage, Screen.OFFSET_X, Screen.OFFSET_Y, screenWidth*3, screenHeight*3, null);
        
        g.dispose(); //borra la imagen
        strat.show();
    }

    public void run(){

        final int NS_PER_SECONDS = 1_000_000_000;
        final byte UPS = 60;
        final double NS_PER_UPDATE = NS_PER_SECONDS / UPS;
        long referenceTime = System.nanoTime();
        long fpsUpsCounterTime = System.nanoTime();
        double timeElapse;
        double delta = 0;

        while(engineOn){
            long startTime = System.nanoTime();
            timeElapse = startTime - referenceTime;
            referenceTime = startTime;
            delta += timeElapse / NS_PER_UPDATE;
    

            while(delta >= 1){
                update();
                delta--;
            }

            display();
            
            if (System.nanoTime() - fpsUpsCounterTime > NS_PER_SECONDS){
                window.setTitle(NAME + ", ups: " + Integer.toString(ups) + ", fps: " + Integer.toString(fps));
                fpsUpsCounterTime = System.nanoTime();
                fps = 0;
                ups = 0;
            }
        }
    }
}
