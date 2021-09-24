package graphics.windows;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maps.Map;
import graphics.Panels;

public class WindowCreateMap extends Canvas{
    private static JFrame window;
    private JPanel panel;
    public WindowCreateMap(){
        setPreferredSize(new Dimension(400,300));
        window = new JFrame("create map");

        panel = PanelCreate.createPanel();

        window.setResizable(false);
        window.setLayout(new BorderLayout());

        window.getContentPane().add(panel, BorderLayout.CENTER);
        window.add(this, BorderLayout.CENTER);
        window.pack();
        
        window.setVisible(true);
    }

    public static JFrame getWindow() {
        return window;
    }
}

class PanelCreate extends JPanel{
    private static JPanel panel;

    public static JPanel createPanel(){
        panel = new PanelCreate();
        panel.setBackground(new Color(195,195,195));
        panel.setBounds(0, 0, 400, 300);
        panel.setLayout(null);
        JTextField width = new JTextField();
        JTextField height = new JTextField();
        JTextField lenght = new JTextField();
        JTextField layer = new JTextField();
        JTextField name = new JTextField();

        width.setBounds(130, 30, 40, 18);
        height.setBounds(130, 50, 40, 18);
        lenght.setBounds(130, 110-18, 40, 18);
        layer.setBounds(130, 135-18, 40, 18);
        name.setBounds(90, 175-18, 83, 18);

        panel.add(width);
        panel.add(height);
        panel.add(lenght);
        panel.add(layer);
        panel.add(name);

        JButton create = new JButton("Create");
        create.setBounds(140,200,100,50);
        create.setBorderPainted(false);
        create.setFocusPainted(false);
        create.setBackground(new Color(105, 255, 255));
        panel.add(create);

        create.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame window = WindowCreateMap.getWindow();
                String w = width.getText();
                int wi = Integer.parseInt(w);
                
                String h = height.getText();
                int he = Integer.parseInt(h);

                String l = layer.getText();
                int la = Integer.parseInt(l);

                String le = lenght.getText();
                int len = Integer.parseInt(le);

                String n = name.getText();
 

                Map map = new Map(n, wi, he, len, la);
                try {
                    map.createMap();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                

                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
        });


        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("MonoSpace",Font.BOLD, 12));
        g.drawString("num Width:", 20, 40);
        g.drawString("cells", 180, 40);

        g.drawString("num Height:", 20, 65);
        g.drawString("cells", 180, 65);

        g.drawString("Cells lenght:", 20, 105);
        g.drawString("px", 180, 105);

        g.drawString("Layers:", 20, 130);
        g.drawString("layers", 180, 130);

        g.drawString("Name:", 20, 170);


    }
}