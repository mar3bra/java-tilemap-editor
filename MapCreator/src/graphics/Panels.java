package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import graphics.windows.WindowCreateMap;
import graphics.windows.WindowLoadMap;
import maps.Map;

public class Panels extends JPanel{
    

    public JPanel topPanel;
    public JPanel leftPanel; 

    private JTextField textArea;

    private int WIDTH;
    private int HEIGHT;

    //Navigation Buttons
    private JButton create;
    private JButton load;

    public JSlider layerSlider;

    public Panels(int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;


        topPanel = new JPanel();
        leftPanel = new JPanel();

        textArea = new JTextField(10);
        create = new JButton("New Map");
        load = new JButton("Load Map");

        
        topPanel.setSize(new Dimension(WIDTH, Screen.OFFSET_Y));
        topPanel.setBackground(new Color(195,195,195));
        
        create.setBounds(0, 0, 100, Screen.OFFSET_Y);
        create.setBorderPainted(false);
        create.setFocusPainted(false);
        create.setBackground(new Color(180, 180, 180));
        topPanel.add(create);

        load.setBounds(110, 0, 120, Screen.OFFSET_Y);
        load.setBorderPainted(false);
        load.setFocusPainted(false);
        load.setBackground(new Color(180, 180, 180));
        topPanel.add(load);
        

        create.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)  {
                WindowCreateMap window = new WindowCreateMap();
                
            }
        });

        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)  {
                WindowLoadMap window = new WindowLoadMap();
                
            }
        });

        //LEFT PANEL----------------------------------------------------------------

        JLabel settings = new JLabel("Settings:");
        JLabel layer = new JLabel("Work on Layer:");     
        layerSlider = new JSlider();
        layerSlider.setBackground(null);
        layerSlider.setValue(3);
        leftPanel.add(settings);
        leftPanel.add(layer);
        leftPanel.add(layerSlider);

        leftPanel.setSize(new Dimension(Screen.OFFSET_X, HEIGHT - Screen.OFFSET_Y));
        leftPanel.setLocation(new Point(0, Screen.OFFSET_Y));
        leftPanel.setBackground(new Color(210,210,210));
        setLayout(null);
        

    }


}



