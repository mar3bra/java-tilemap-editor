package maps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Map implements Serializable{
    public final String name;
    private final int width;
    private final int height;
    private final int large;
    private int[][] layer;
    //private int[][] hitBox;

    public Map(String name, int width, int height, int large, int layers){
        this.name = name;
        this.width = width;
        this.height = height;
        this.large = large;

        this.layer = new int[layers][width*height];
    }

    public void createMap() throws IOException{
        FileOutputStream file = new FileOutputStream(new File("src/resources/" + name + ".txt"));
        ObjectOutputStream object = new ObjectOutputStream(file);

        object.writeObject(this);

        object.close();
        file.close();
    } 

    public static Map readMap(String name) throws IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream("src/resources/" + name);
        ObjectInputStream object = new ObjectInputStream(file);
        Map map = (Map) object.readObject();
        object.close();

        
        return map;
    }
}


