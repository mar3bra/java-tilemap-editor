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
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;

import maps.Map;
import graphics.Panels;

public class WindowLoadMap extends Canvas{
    private static JFrame window;
    private JPanel panel;
    public WindowLoadMap(){
        setPreferredSize(new Dimension(400,300));
        window = new JFrame("load map");

        panel = PanelLoad.createPanel();

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

class PanelLoad extends JPanel{
    private static JPanel panel;

    public static JPanel createPanel(){
        panel = new PanelLoad();
        panel.setBackground(new Color(195,195,195));
        panel.setBounds(0, 0, 400, 300);
        panel.setLayout(null);
        JTextField rute = new JTextField();

        File files = new File("src/resources/");
        String[] mapList = files.list();
        JList list = new JList(mapList);

        rute.setBounds(90, 27, 200, 18);
        list.setBounds(90,55,200,100);

        panel.add(rute);
        panel.add(list);


        JButton load = new JButton("Load");
        load.setBounds(140,200,100,50);
        load.setBorderPainted(false);
        load.setFocusPainted(false);
        load.setBackground(new Color(105, 255, 255));
        panel.add(load);

        load.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame window = WindowLoadMap.getWindow();     

                String fileName = list.getSelectedValue().toString();

               

                try {
                    Map map = Map.readMap(fileName);
                    System.out.println(map.name);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
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
        g.drawString("File rute:", 20, 40);

        g.drawString("File List:", 20, 65);

    }
}