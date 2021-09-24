package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

    private final static int TOTAL_KEYS = 200;
    private final Boolean[] KeyList = new Boolean[TOTAL_KEYS];
    public Boolean a = false;
    public Boolean w = false;
    public Boolean s = false;
    public Boolean d = false;

    public void update(){   
             
        if (KeyList[KeyEvent.VK_A] == null) {
            
        } else {
        a = KeyList[KeyEvent.VK_A];
        }
        /*w = KeyList[KeyEvent.VK_W];
        s = KeyList[KeyEvent.VK_S];
        d = KeyList[KeyEvent.VK_D];
        */
        //System.out.println(KeyList[KeyEvent.VK_A]);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyList[e.getKeyCode()] = true;  
        System.out.println("hola");     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyList[e.getKeyCode()] = false;        
    }

    @Override
    public void keyTyped(KeyEvent e) {      
    }
    
}
